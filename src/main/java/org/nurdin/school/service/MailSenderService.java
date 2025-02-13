package org.nurdin.school.service;

import org.nurdin.school.dto.FormAcceptTheBidForStudyDTO;
import org.nurdin.school.dto.FormRejectTheBidForStudyDTO;
import org.nurdin.school.dto.InvitationToInterviewForWorkDTO;
import org.nurdin.school.dto.RejectionOfTheBidForWorkDTO;
import org.nurdin.school.entity.UserEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service

public class MailSenderService {

    private final JavaMailSender mailSender;

    public MailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendMail(String to, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);

        mailSender.send(mailMessage);
    }

    public void sendMailToInterviewForWork(UserEntity user , InvitationToInterviewForWorkDTO invitationToInterviewForWorkDTO) {
        String invitation = invitationToInterviewForWorkDTO.getInvitation();
        String interviewDate = invitationToInterviewForWorkDTO.getInterviewDate();

        sendMail(
                user.getEmail(),
                user.getUsername(),
                invitation + interviewDate
        );
    }

    public void sendMailToRejectionForWork(UserEntity user , RejectionOfTheBidForWorkDTO rejectionOfTheBidForWorkDTO) {
        String mail = rejectionOfTheBidForWorkDTO.getMail();

        sendMail(
                user.getEmail(),
                user.getUsername(),
                mail
        );
    }

    public void sendMailToInterviewForStudy(UserEntity user , FormAcceptTheBidForStudyDTO formAcceptTheBidForStudyDTO) {
        String mail = formAcceptTheBidForStudyDTO.getMailForAcceptBid() + formAcceptTheBidForStudyDTO.getMeetingDate();

        sendMail(
                user.getEmail(),
                user.getUsername(),
                mail
        );
    }

    public void sendMailToRejectBidForStudy(UserEntity user , FormRejectTheBidForStudyDTO formRejectTheBidForStudyDTO) {
        sendMail(
                user.getEmail(),
                user.getUsername(),
                formRejectTheBidForStudyDTO.getMailForRejectTheBid()
        );
    }



}
