package org.nurdin.school.service;


import org.nurdin.school.dto.LoginDTO;
import org.nurdin.school.dto.VerifyUserDTO;
import org.nurdin.school.entity.UserEntity;

public interface AuthService {
    UserEntity login(LoginDTO loginDTO);
    void verifyUser(VerifyUserDTO user);
    void resendVerificationCode(String email);
    void sendVerificationEmail(UserEntity user);
    String generateVerificationCode();
}
