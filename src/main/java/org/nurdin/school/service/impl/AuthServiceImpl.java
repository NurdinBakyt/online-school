package org.nurdin.school.service.impl;

import org.nurdin.school.dto.LoginDTO;
import org.nurdin.school.dto.VerifyUserDTO;
import org.nurdin.school.dto.response.LoginDtoResponse;
import org.nurdin.school.entity.UserEntity;
import org.nurdin.school.exceptions.UserNotFoundException;
import org.nurdin.school.repository.UserRepository;
import org.nurdin.school.security.JwtService;
import org.nurdin.school.service.AuthService;
import org.nurdin.school.service.MailSenderService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final MailSenderService mailSenderService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public AuthServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager,
                           MailSenderService mailSenderService, JwtService jwtService,
                           UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.mailSenderService = mailSenderService;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public LoginDtoResponse login(LoginDTO loginDTO) {
        UserEntity user = userRepository.findByUsername(loginDTO.getIdentifier())
                .orElseGet(() -> userRepository.findByEmail(loginDTO.getIdentifier())
                        .orElseThrow(() -> new RuntimeException("ты гей")));

        if (!user.isEnabled()) {
            throw new RuntimeException("Вы не верифицированы. Подтвердите почту");
        }

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getIdentifier(),
                        loginDTO.getPassword()
                )
        );

        String accessToken = jwtService.generateToken(user);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), user);
        Long expireIn = jwtService.getExpirationTime();

        return new LoginDtoResponse(accessToken, refreshToken, expireIn);
    }

    @Override
    public String refreshAccessToken(String refreshToken) {
        String identifier = jwtService.extractUsernameFromToken(refreshToken);
        System.out.println("Извлеченный user " + identifier);

        UserEntity user = userRepository.findByUsername(identifier)
                .orElseGet(() -> userRepository.findByEmail(identifier)
                        .orElseThrow(() -> new RuntimeException("Пользователь не найден")));

        if (!jwtService.isTokenValid(refreshToken, user)) {
            throw new RuntimeException("Недействительный токен");
        }

        return jwtService.generateToken(user);
    }


    @Override
    public void verifyUser(VerifyUserDTO verifyUserDTO) {
        UserEntity user = userRepository.findByEmail(verifyUserDTO.getEmail())
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

        if (user.getVerificationCodeExpiration().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Код верификации истек");
        }

        if (!user.getVerificationCode().equals(verifyUserDTO.getVerificationCode())) {
            throw new RuntimeException("Неверный код верификации");
        }
        if (user.isEnabled()) {
            throw new RuntimeException("Аккаунт уже верифицирован");
        }

        user.setEnabled(true);
        user.setVerificationCode(null);
        user.setVerificationCodeExpiration(null);
        userRepository.save(user);
    }

    @Override
    public void resendVerificationCode(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

        if (user.isEnabled()) {
            throw new RuntimeException("Аккаунт уже верифицирован");
        }

        user.setVerificationCode(generateVerificationCode());
        user.setVerificationCodeExpiration(LocalDateTime.now().plusMinutes(15));
        sendVerificationEmail(user);
        userRepository.save(user);
    }

    @Override
    public void sendVerificationEmail(UserEntity user) {
        String subject = "Верификация аккаунта";
        String verificationCode = user.getVerificationCode();
        String htmlMessage = "<html>"
                + "<body style=\"font-family: Arial, sans-serif;\">"
                + "<div style=\"background-color: #f5f5f5; padding: 20px;\">"
                + "<h2 style=\"color: #333;\">Добро пожаловать!</h2>"
                + "<p style=\"font-size: 16px;\">Пожалуйста, введите код верификации:</p>"
                + "<div style=\"background-color: #fff; padding: 20px; border-radius: 5px; "
                + "box-shadow: 0 0 10px rgba(0,0,0,0.1);\">"
                + "<h3 style=\"color: #333;\">Код:</h3>"
                + "<p style=\"font-size: 18px; font-weight: bold; color: #007bff;\">" + verificationCode + "</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        mailSenderService.sendMail(user.getEmail(), subject, htmlMessage);
    }

    @Override
    public String generateVerificationCode() {
        return String.valueOf(new Random().nextInt(900000) + 100000);
    }

    @Override
    public Long getAccessTokenExpiration() {
        return jwtService.getExpirationTime();
    }
}
