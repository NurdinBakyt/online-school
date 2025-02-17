package org.nurdin.school.service;

import org.nurdin.school.dto.LoginDTO;
import org.nurdin.school.dto.VerifyUserDTO;
import org.nurdin.school.dto.response.LoginDtoResponse;
import org.nurdin.school.entity.UserEntity;

public interface AuthService {
    LoginDtoResponse login(LoginDTO loginDTO);
    String refreshAccessToken(String refreshToken);
    void verifyUser(VerifyUserDTO verifyUserDTO);
    void resendVerificationCode(String email);
    void sendVerificationEmail(UserEntity user);
    String generateVerificationCode();
    Long getAccessTokenExpiration();
}
