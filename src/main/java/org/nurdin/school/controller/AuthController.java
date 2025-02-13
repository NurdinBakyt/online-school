package org.nurdin.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import org.nurdin.school.enums.RolesEnum;
import org.nurdin.school.enums.RolesRegisterEnum;
import org.nurdin.school.enums.UserStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Schema(name = "controller который показывает данные. Инфу про авторизацию,роли,статусы")
@RestController
@RequestMapping("api/v1/auth")
public class AuthController {

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