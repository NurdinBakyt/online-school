package org.nurdin.school.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.nurdin.school.dto.FormAcceptTheBidForStudyDTO;
import org.nurdin.school.dto.FormRejectTheBidForStudyDTO;
import org.nurdin.school.dto.InvitationToInterviewForWorkDTO;
import org.nurdin.school.dto.RejectionOfTheBidForWorkDTO;
import org.nurdin.school.entity.UserEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    private final JavaMailSender mailSender;

    public MailSenderService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendMail(String to, String subject, String text) {

        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true,"UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Ошибка при отправке email: " + e.getMessage(), e);
        }
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

    public void sendApproveMail (UserEntity user , String mailForUser) {
        sendMail(
            user.getEmail(),
            user.getUsername(),
            mailForUser
        );
}
}
