package org.nurdin.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.nurdin.school.dto.BidForStudyDTO;
import org.nurdin.school.dto.FormAcceptTheBidForStudyDTO;
import org.nurdin.school.dto.FormRejectTheBidForStudyDTO;
import org.nurdin.school.dto.response.BidForStudyResponse;
import org.nurdin.school.entity.BidForStudyEntity;
import org.nurdin.school.entity.UserEntity;
import org.nurdin.school.enums.StatusOfBid;
import org.nurdin.school.service.*;
import org.nurdin.school.util.mappers.BidForStudyMapper;
import org.nurdin.school.util.mappers.FormAcceptTheBidMapper;
import org.nurdin.school.util.mappers.FormRejectTheBidForStudyMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bidForStudy")
public class BidForStudyController {

    private final BidForStudyService bidForStudyService;
    private final BidForStudyMapper bidForStudyMapper;
    private final UserService userService;
    private final MailSenderService mailSenderService;
    private final FormAcceptTheBidService formAcceptTheBidService;
    private final FormAcceptTheBidMapper formAcceptTheBidMapper;
    private final FormRejectTheBidForStudyService formRejectTheBidForStudyService;
    private final FormRejectTheBidForStudyMapper formRejectTheBidForStudyMapper;


    public BidForStudyController(BidForStudyService bidForStudyService, BidForStudyMapper bidForStudyMapper, UserService userService, MailSenderService mailSenderService, FormAcceptTheBidService formAcceptTheBidService, FormAcceptTheBidMapper formAcceptTheBidMapper, FormRejectTheBidForStudyService formRejectTheBidForStudyService, FormRejectTheBidForStudyMapper formRejectTheBidForStudyMapper) {
        this.bidForStudyService = bidForStudyService;
        this.bidForStudyMapper = bidForStudyMapper;
        this.userService = userService;
        this.mailSenderService = mailSenderService;
        this.formAcceptTheBidService = formAcceptTheBidService;
        this.formAcceptTheBidMapper = formAcceptTheBidMapper;
        this.formRejectTheBidForStudyService = formRejectTheBidForStudyService;
        this.formRejectTheBidForStudyMapper = formRejectTheBidForStudyMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<String> createBidForStudy (@RequestBody BidForStudyDTO bidForStudyDTO) {
        bidForStudyService.saveBidForStudy(bidForStudyMapper.bidForStudyDTOToEntity(bidForStudyDTO));
        return ResponseEntity.ok("заявка успешно создана");
    }

    @Operation(summary = "получение всех заявок для учебы")
    @GetMapping("get_all")
    public List<BidForStudyEntity> getAllBidForStudy() {
        return bidForStudyService.getAllBidForStudy();
    }

    @PostMapping("/accept_bid_for_study")
    public BidForStudyResponse acceptBidForStudy (@RequestBody FormAcceptTheBidForStudyDTO formAcceptTheBidForStudyDTO, @RequestParam String emailParent) {

        BidForStudyEntity bidForStudyEntity = bidForStudyService.findByEmailParent(emailParent);
        bidForStudyEntity.setBidStatus(StatusOfBid.INTERVIEWEE);
        bidForStudyService.saveBidForStudy(bidForStudyEntity);

        UserEntity userEntity  = userService.findByEmail(emailParent);

        mailSenderService.sendMailToInterviewForStudy(userEntity, formAcceptTheBidForStudyDTO);

        formAcceptTheBidService.saveFormAcceptTheBid(formAcceptTheBidMapper.formAcceptTheBidDTOtoEntity(formAcceptTheBidForStudyDTO));

    return bidForStudyMapper.createBidForStudyResponse(bidForStudyEntity , userEntity.getUsername());
    }

    @PostMapping("/reject_bid_for_study")
    public BidForStudyResponse rejectBidForStudy (@RequestBody FormRejectTheBidForStudyDTO formRejectTheBidForStudyDTO, @RequestParam String emailParent) {

        BidForStudyEntity bidForStudyEntity = bidForStudyService.findByEmailParent(emailParent);
        bidForStudyEntity.setBidStatus(StatusOfBid.REJECTED);
        bidForStudyService.saveBidForStudy(bidForStudyEntity);

        UserEntity userEntity  = userService.findByEmail(emailParent);

        formRejectTheBidForStudyService.save(formRejectTheBidForStudyMapper.formRejectionForTheBidDTOtoEntity(formRejectTheBidForStudyDTO));

        mailSenderService.sendMailToRejectBidForStudy(userEntity, formRejectTheBidForStudyDTO);
        return null;
    }

    @PostMapping("/approve_the_bid_for_study")
    public ResponseEntity<String> approveBidForStudy (@RequestParam String email , @RequestParam String emailForParent) {
        BidForStudyEntity bidForStudyEntity = bidForStudyService.findByEmailParent(email);

        bidForStudyEntity.setBidStatus(StatusOfBid.ACCEPTED);
        bidForStudyService.saveBidForStudy(bidForStudyEntity);

        UserEntity user  = userService.findByEmail(email);



        mailSenderService.sendApproveMail(user, emailForParent);
        return ResponseEntity.ok("заявка одобрена");
    }


}
