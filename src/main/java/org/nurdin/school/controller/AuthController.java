package org.nurdin.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.nurdin.school.dto.LoginDTO;
import org.nurdin.school.dto.VerifyUserDTO;
import org.nurdin.school.dto.response.LoginDtoResponse;
import org.nurdin.school.enums.RolesEnum;
import org.nurdin.school.enums.RolesRegisterEnum;
import org.nurdin.school.enums.UserStatus;
import org.nurdin.school.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDtoResponse> login(@RequestBody LoginDTO loginDTO) {
        LoginDtoResponse response = authService.login(loginDTO);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginDtoResponse> refresh(@RequestBody Map<String, String> request) {
        String refreshToken = request.get("refreshToken");
        if (refreshToken == null) {
            throw new RuntimeException("token is null");
        }

        String newAccessToken = authService.refreshAccessToken(refreshToken);
        Long expireIn = authService.getAccessTokenExpiration();

        return ResponseEntity.ok(new LoginDtoResponse(newAccessToken, refreshToken, expireIn));
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestBody VerifyUserDTO verifyUserDTO) {
        authService.verifyUser(verifyUserDTO);
        return ResponseEntity.ok("Аккаунт успешно верифицирован!");
    }

    @PostMapping("/resend-verification")
    public ResponseEntity<String> resendVerification(@RequestParam String email) {
        authService.resendVerificationCode(email);
        return ResponseEntity.ok("Новый код верификации отправлен!");
    }

    @GetMapping("/user-status")
    @Operation(summary = "Показывает статус пользователя, активный или заблокирован и т.д")
    public ResponseEntity<UserStatus[]> getStatus() {
        return ResponseEntity.ok(UserStatus.values());
    }

    @GetMapping("/user-register-roles")
    @Operation(summary = "Показывает все доступные роли при регистрации ", description = "Не показывает все доступные роли в системе а только которые доступны при регистрации ")
    public ResponseEntity<RolesRegisterEnum[]> getUserRegisterRoles() {
        return ResponseEntity.ok(RolesRegisterEnum.values());
    }

    @GetMapping("/user-roles")
    @Operation(summary = "Все доступные роли в общем", description = " На пример в методе getUserRegisterRoles мы получаем все роли доступные при регистрации, а в этом методе все роли которые у есть у нас в системе ")
    public ResponseEntity<RolesEnum[]> getUserRoles() {
        return ResponseEntity.ok(RolesEnum.values());
    }
}
