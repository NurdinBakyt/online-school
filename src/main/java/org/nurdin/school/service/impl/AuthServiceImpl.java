package org.nurdin.school.service.impl;

import org.nurdin.school.dto.LoginDTO;
import org.nurdin.school.dto.VerifyUserDTO;
import org.nurdin.school.entity.UserEntity;
import org.nurdin.school.exceptions.UserNotFoundException;
import org.nurdin.school.repository.UserRepository;
import org.nurdin.school.service.AuthService;
import org.nurdin.school.service.MailSenderService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final MailSenderService mailSenderService;

    public AuthServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, MailSenderService mailSenderService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.mailSenderService = mailSenderService;
    }

    @Override
    public UserEntity login(LoginDTO loginDTO) {
        UserEntity user = userRepository.findByEmail(loginDTO.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
        if (!user.isEnabled()){
            throw new RuntimeException("Вы не верифецированы. Подтвердите почту");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getEmail(),
                        loginDTO.getPassword()
                )
        );
        return user;
    }

    @Override
    public void verifyUser(VerifyUserDTO verifyUserDTO) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(verifyUserDTO.getEmail());
        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            if (user.getVerificationCodeExpiration().isBefore(LocalDateTime.now())){
                System.out.println(user.getVerificationCodeExpiration());
                throw new RuntimeException("Код верификации истек");
            }
            System.out.println(user.getVerificationCode());
            if (user.getVerificationCode().equals(verifyUserDTO.getVerificationCode())){
                user.setEnabled(true);
                user.setVerificationCode(null);
                user.setVerificationCodeExpiration(null);
                userRepository.save(user);
            }
        }else{
            throw new UserNotFoundException("Пользователь не найден");
        }
    }

    @Override
    public void resendVerificationCode(String email) {
        Optional<UserEntity> userEntity = userRepository.findByEmail(email);
        if (userEntity.isPresent()) {
            UserEntity user = userEntity.get();
            if (user.isEnabled()){
                throw new RuntimeException("Аккаунт верифицирован");
            }
            user.setVerificationCode(generateVerificationCode());
            user.setVerificationCodeExpiration(LocalDateTime.now().plusMinutes(15));
            sendVerificationEmail(user);
            userRepository.save(user);
        }else{
            throw new UserNotFoundException("Пользователь не найден");
        }
    }

    @Override
    public void sendVerificationEmail(UserEntity user) {
        String subject = "Верификация аккаунта ";
        String verificationCode = user.getVerificationCode();
        String htmlMessage = "<html>"
                + "<body style=\"font-family: Arial, sans-serif;\">"
                + "<div style=\"background-color: #f5f5f5; padding: 20px;\">"
                + "<h2 style=\"color: #333;\">Добро пожаловать!</h2>"
                + "<p style=\"font-size: 16px;\">Пожалуйста введите код верификации:</p>"
                + "<div style=\"background-color: #fff; padding: 20px; border-radius: 5px; box-shadow: 0 0 10px rgba(0,0,0,0.1);\">"
                + "<h3 style=\"color: #333;\">Код:</h3>"
                + "<p style=\"font-size: 18px; font-weight: bold; color: #007bff;\">" + verificationCode + "</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        try {
            mailSenderService.sendMail(user.getEmail(), subject, htmlMessage);
        }catch (RuntimeException e){
            e.printStackTrace();
        }
    }

    @Override
    public String generateVerificationCode() {
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);
    }

}
