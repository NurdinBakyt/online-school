package org.nurdin.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.nurdin.school.dto.BidForWorkDTO;
import org.nurdin.school.dto.InvitationToInterviewForWorkDTO;
import org.nurdin.school.dto.RejectionOfTheBidForWorkDTO;
import org.nurdin.school.dto.response.BidForWorkResponse;
import org.nurdin.school.entity.BidForStudyEntity;
import org.nurdin.school.entity.BidForWorkEntity;
import org.nurdin.school.entity.UserEntity;
import org.nurdin.school.enums.StatusOfBid;
import org.nurdin.school.service.*;
import org.nurdin.school.util.mappers.BidForWorkMapper;
import org.nurdin.school.util.mappers.InvitationToInterviewMapper;
import org.nurdin.school.util.mappers.RejectionOfTheBidForWorkMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    private final RejectionOfTheBidForWorkMapper rejectionOfTheBidForWorkMapper;
    private final BidForWorkMapper bidForWorkMapper;

    public BidForWorkController(BidForWorkService bidForWorkService, UserService userService, MailSenderService mailSenderService, InvitationToInterviewService invitationToInterviewService, InvitationToInterviewMapper invitationToInterviewMapper, RejectionOfTheBidService rejectionOfTheBidService, RejectionOfTheBidForWorkMapper rejectionOfTheBidForWorkMapper, BidForWorkMapper bidForWorkMapper) {
        this.bidForWorkService = bidForWorkService;
        this.userService = userService;
        this.mailSenderService = mailSenderService;
        this.invitationToInterviewService = invitationToInterviewService;
        this.invitationToInterviewMapper = invitationToInterviewMapper;
        this.rejectionOfTheBidService = rejectionOfTheBidService;
        this.rejectionOfTheBidForWorkMapper = rejectionOfTheBidForWorkMapper;
        this.bidForWorkMapper = bidForWorkMapper;
    }

    @PostMapping(value = "/createBidForWork")
    @Operation(summary = "Создание заявки на работу")
    public ResponseEntity<String> createBidForWork(@RequestBody BidForWorkDTO bidForWorkDTO) {


        bidForWorkService.saveBidForWork(bidForWorkMapper.bidForWorkDtoToEntity(bidForWorkDTO));
        return ResponseEntity.ok("заявка успешно создана");
    }

    @Operation(summary = "принятие заявки для работы")
    @PostMapping(value = "/acceptBid")
    public BidForWorkResponse acceptTheBid (@RequestParam Long idBid ,@RequestParam String emailUser, @RequestBody InvitationToInterviewForWorkDTO invitationToInterviewForWorkDTO) {
        //в общем какая у меня была ошибка я в параметре не указал анотацию @RequestParam которая говорит что
        //мы в параметр принимаем Json-ку а до этого мы просто принимали пустоту без этой анотации
        //насчет анотации @RequestParam изучить и еще анотацию @PathVariable
        BidForWorkEntity bidForWorkEntity = bidForWorkService.findById(idBid).get();
        bidForWorkEntity.setBidStatus(StatusOfBid.INTERVIEWEE);
        bidForWorkService.saveBidForWork(bidForWorkEntity);


        UserEntity user = userService.findByEmail(emailUser);

        invitationToInterviewService.save(invitationToInterviewMapper.invitationToInterviewDTOtoEntity(invitationToInterviewForWorkDTO));

        mailSenderService.sendMailToInterviewForWork(user, invitationToInterviewForWorkDTO);


        return bidForWorkMapper.bidForWorkEntityAndUserEntityToBidForWorkAcceptResponse(user ,bidForWorkEntity );

    }


    @Operation(summary = "отклонение заявки для работы")
    @PostMapping(value = "/rejectTheBid")
    public BidForWorkResponse rejectTheBid (@RequestParam Long idBid ,@RequestParam String emailUser, @RequestBody RejectionOfTheBidForWorkDTO rejectionOfTheBidForWorkDTO) {
        BidForWorkEntity bidForWorkEntity = bidForWorkService.findById(idBid).get();
        bidForWorkEntity.setBidStatus(StatusOfBid.REJECTED);
        bidForWorkService.saveBidForWork(bidForWorkEntity);

        UserEntity user = userService.findByEmail(emailUser);


        rejectionOfTheBidService.save(rejectionOfTheBidForWorkMapper.rejectionOfTheBidDTOtoEntity(rejectionOfTheBidForWorkDTO));

        mailSenderService.sendMailToRejectionForWork(user, rejectionOfTheBidForWorkDTO);
        return bidForWorkMapper.bidForWorkEntityAndUserEntityToBidForWorkAcceptResponse(user ,bidForWorkEntity );

    }

    @Operation(summary = "получение всех заявок для работы")
    @GetMapping(value = "/getAllBids")
    public List<BidForWorkEntity> getAllBidForWork() {
        return bidForWorkService.getAllBidForWork();
    }

    @Operation(summary = "одобрение заявки для работы")
    @PostMapping("/approve_the_bid_for_work")
    public ResponseEntity<String> approveBidForStudy (@RequestParam Long idBid ,@RequestParam String emailUser , @RequestParam String mailForUser) {
        BidForWorkEntity bidForWorkEntity = bidForWorkService.findById(idBid).get();
        bidForWorkEntity.setBidStatus(StatusOfBid.ACCEPTED);
        bidForWorkService.saveBidForWork(bidForWorkEntity);

        UserEntity user  = userService.findByEmail(emailUser);



        mailSenderService.sendApproveMail(user, mailForUser);
        return ResponseEntity.ok("заявка одобрена");
    }
}
