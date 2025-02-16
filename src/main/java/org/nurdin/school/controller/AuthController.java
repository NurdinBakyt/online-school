package org.nurdin.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.nurdin.school.dto.LoginDTO;
import org.nurdin.school.dto.VerifyUserDTO;
import org.nurdin.school.dto.response.LoginDtoResponse;
import org.nurdin.school.entity.UserEntity;
import org.nurdin.school.enums.RolesEnum;
import org.nurdin.school.enums.RolesRegisterEnum;
import org.nurdin.school.enums.UserStatus;
import org.nurdin.school.security.JwtService;
import org.nurdin.school.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Schema(name = "controller который показывает данные. Инфу про авторизацию,роли,статусы")
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {
    private final JwtService jwtService;
    private final AuthService authService;

    public AuthController(JwtService jwtService, AuthService authService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDtoResponse> loginDtoResponseResponseEntity(@RequestBody LoginDTO loginDTO){
        UserEntity user = authService.login(loginDTO);
        String jwtToken = jwtService.generateToken(user);
        LoginDtoResponse loginDtoResponse = new LoginDtoResponse(jwtToken,jwtService.getExpirationTime());
        return ResponseEntity.ok(loginDtoResponse);
    }
    @PostMapping("/verify")
    public ResponseEntity<?> verifyUser(@RequestBody VerifyUserDTO verifyUserDTO){
        try {
            authService.verifyUser(verifyUserDTO);
            return ResponseEntity.ok("Аккаунт успешно верифицирован");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/resend")
    public ResponseEntity<?> resendUser(@RequestParam String email){
        try {
            authService.resendVerificationCode(email);
            return ResponseEntity.ok("Код верификации выслан");
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/user-status")
    @Operation(summary = "ENUM которая показывает статусы пользователя, активный или заблокирован и т.д")
    public ResponseEntity<UserStatus[]> getStatus() {
        return ResponseEntity.ok(UserStatus.values());
    }

    @GetMapping("/user-register-roles")
    @Operation(summary = "ENUM которая показывает все доступные роли при регистрации ", description = "Это get запрос который просто выводит роли при регистрации.из только 2 EMPLOYEE,PARENT")
    public ResponseEntity<RolesRegisterEnum[]> getUserRegisterRoles() {
        return ResponseEntity.ok(RolesRegisterEnum.values());
    }

    @GetMapping("/user-roles")
    @Operation(summary = "Роли все которые есть в системе", description = " На пример в методе getUserRegisterRoles мы получаем все роли доступные при регистрации, а в этом методе все роли которые у есть у нас в системе ")
    public ResponseEntity<RolesEnum[]> getUserRoles() {
        return ResponseEntity.ok(RolesEnum.values());
    }
}