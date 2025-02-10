package org.nurdin.school.service;

import org.nurdin.school.dto.InvitationToInterviewDTO;
import org.nurdin.school.dto.RejectionOfTheBidDTO;
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

    public void sendMailToInterview (UserEntity user , InvitationToInterviewDTO invitationToInterviewDTO) {
        String userName = user.getUsername();
        String invitation = invitationToInterviewDTO.getInvitation();
        String interviewDate = invitationToInterviewDTO.getInterviewDate();
        String messageForUser = invitation + interviewDate;

        sendMail(
                user.getEmail(),
                user.getUsername(),
                invitation + interviewDate
        );
    }

    public void sendMailToRejection (UserEntity user , RejectionOfTheBidDTO rejectionOfTheBidDTO) {
        String userName = user.getUsername();
        String mail = rejectionOfTheBidDTO.getMail();


        sendMail(
                user.getEmail(),
                user.getUsername(),
                mail
        );
    }

}
