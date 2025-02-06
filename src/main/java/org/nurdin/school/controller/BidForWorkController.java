package org.nurdin.school.controller;

import org.nurdin.school.entity.BidForWorkEntity;
import org.nurdin.school.entity.UserEntity;
import org.nurdin.school.enums.StatusOfBid;
import org.nurdin.school.service.BidForWorkService;
import org.nurdin.school.service.MailSenderService;
import org.nurdin.school.service.UserService;
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

    public BidForWorkController(BidForWorkService bidForWorkService, UserService userService, MailSenderService mailSenderService) {
        this.bidForWorkService = bidForWorkService;
        this.userService = userService;
        this.mailSenderService = mailSenderService;
    }

    @PostMapping(value = "/acceptBid/")
    public ResponseEntity<String> acceptTheBid (@RequestParam String email) {
        //в общем какая у меня была ошибка я в параметре не указал анотацию @RequestParam которая говорит что
        //мы в параметр принимаем Json-ку а до этого мы просто принимали пустоту без этой анотации
        //насчет анотации @RequestParam изучить и еще анотацию @PathVariable

       BidForWorkEntity bidForWorkEntity = bidForWorkService.getBidForWorkByUserEmail(email);
       bidForWorkEntity.setBidStatus(StatusOfBid.ACCEPTED);
       bidForWorkService.saveBidForWork(bidForWorkEntity);

       UserEntity user = userService.findByEmail(email);
       String userName = user.getUsername();

       if (userName == null){
           System.out.println("НЕТУ");
       }

       //еще надо дто формы принятия таблицы
        // в это таблице сделать поля дата, письмо
        // сделать из нее - сформировать text письма
        mailSenderService.sendMail(
                email,
                userName + "ваша заявка была вы приглашенны на собеседование",
                //text пока временно сам напишу
                "вот ты красава в общем погнали на собес в субботу к 16:00"


        );
       return ResponseEntity.ok("Статус пользователя изменен на ACCEPTED");



    }
    @PostMapping(value = "/rejectTheBid")
    public ResponseEntity<String> rejectTheBid (@RequestParam String email ) {
        BidForWorkEntity bidForWorkEntity = bidForWorkService.getBidForWorkByUserEmail(email);
        bidForWorkEntity.setBidStatus(StatusOfBid.REJECTED);
        bidForWorkService.saveBidForWork(bidForWorkEntity);
        return ResponseEntity.ok("Статус пользователя изменен на REJECTED");
    }

    @GetMapping(value = "/getAllBids")
    public List<BidForWorkEntity> getAllBidForWork() {
        return bidForWorkService.getAllBidForWork();
    }
}
