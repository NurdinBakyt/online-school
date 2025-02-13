
package org.nurdin.school.controller;

import org.nurdin.school.dto.InvitationToInterviewDTO;
import org.nurdin.school.dto.RejectionOfTheBidDTO;
import org.nurdin.school.dto.response.BidForWorkResponse;
import org.nurdin.school.entity.BidForWorkEntity;
import org.nurdin.school.entity.UserEntity;
import org.nurdin.school.enums.StatusOfBid;
import org.nurdin.school.service.*;
import org.nurdin.school.util.BidForWorkMapper;
import org.nurdin.school.util.InvitationToInterviewMapper;
import org.nurdin.school.util.RejectionOfTheBidMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/v1/bidForWork")
public class BidForWorkController {
    private static final Logger log = LoggerFactory.getLogger(BidForWorkController.class);
    private final BidForWorkService bidForWorkService;
    private final UserService userService;
    private final MailSenderService mailSenderService;
    private final InvitationToInterviewService invitationToInterviewService;
    private final InvitationToInterviewMapper invitationToInterviewMapper;
    private final RejectionOfTheBidService rejectionOfTheBidService;
    private final RejectionOfTheBidMapper rejectionOfTheBidMapper;
    private final BidForWorkMapper bidForWorkMapper;

    public BidForWorkController(BidForWorkService bidForWorkService, UserService userService, MailSenderService mailSenderService, InvitationToInterviewService invitationToInterviewService, InvitationToInterviewMapper invitationToInterviewMapper, RejectionOfTheBidService rejectionOfTheBidService, RejectionOfTheBidMapper rejectionOfTheBidMapper, BidForWorkMapper bidForWorkMapper) {
        this.bidForWorkService = bidForWorkService;
        this.userService = userService;
        this.mailSenderService = mailSenderService;
        this.invitationToInterviewService = invitationToInterviewService;
        this.invitationToInterviewMapper = invitationToInterviewMapper;
        this.rejectionOfTheBidService = rejectionOfTheBidService;
        this.rejectionOfTheBidMapper = rejectionOfTheBidMapper;
        this.bidForWorkMapper = bidForWorkMapper;
    }

    @PostMapping(value = "/acceptBid")
    public BidForWorkResponse acceptTheBid (@RequestParam String email, @RequestBody InvitationToInterviewDTO invitationToInterviewDTO) {
        //в общем какая у меня была ошибка я в параметре не указал анотацию @RequestParam которая говорит что
        //мы в параметр принимаем Json-ку а до этого мы просто принимали пустоту без этой анотации
        //насчет анотации @RequestParam изучить и еще анотацию @PathVariable
        BidForWorkEntity bidForWorkEntity = bidForWorkService.getBidForWorkByUserEmail(email);
        bidForWorkEntity.setBidStatus(StatusOfBid.INTERVIEWEE);
        bidForWorkService.saveBidForWork(bidForWorkEntity);


        UserEntity user = userService.findByEmail(email);

        invitationToInterviewService.save(invitationToInterviewMapper.invitationToInterviewDTOtoEntity(invitationToInterviewDTO));

        mailSenderService.sendMailToInterview(user,invitationToInterviewDTO);


        return bidForWorkMapper.bidForWorkEntityAndUserEntityToBidForWorkAcceptResponse(user ,bidForWorkEntity );

    }



    @PostMapping(value = "/rejectTheBid")
    public BidForWorkResponse rejectTheBid (@RequestParam String email , @RequestBody RejectionOfTheBidDTO rejectionOfTheBidDTO) {
        BidForWorkEntity bidForWorkEntity = bidForWorkService.getBidForWorkByUserEmail(email);
        bidForWorkEntity.setBidStatus(StatusOfBid.REJECTED);
        bidForWorkService.saveBidForWork(bidForWorkEntity);

        UserEntity user = userService.findByEmail(email);


        rejectionOfTheBidService.save(rejectionOfTheBidMapper.rejectionOfTheBidDTOtoEntity(rejectionOfTheBidDTO));

        mailSenderService.sendMailToRejection(user,rejectionOfTheBidDTO);
        return bidForWorkMapper.bidForWorkEntityAndUserEntityToBidForWorkAcceptResponse(user ,bidForWorkEntity );

    }


    @GetMapping(value = "/getAllBids")
    public List<BidForWorkEntity> getAllBidForWork() {
        return bidForWorkService.getAllBidForWork();
    }
}
