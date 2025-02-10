package org.nurdin.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.nurdin.school.dto.RoleDTO;
import org.nurdin.school.entity.RoleEntity;
import org.nurdin.school.service.RoleService;
import org.nurdin.school.util.RolesMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/role")
public class RoleController {
    private final RoleService roleService;
    private final RolesMapper rolesMapper;

    public RoleController(RoleService roleService, RolesMapper rolesMapper) {
        this.roleService = roleService;
        this.rolesMapper = rolesMapper;
    }

    @GetMapping("/get-role-by-id/{id}")
    @Operation(summary = "get role by id, в переводе означает 'получить роль по идентификатору'", description = "то есть метод позволяет по id получить роль, естественно если они совпадают")
    public ResponseEntity<RoleEntity> getRoleById(@PathVariable Long id) {
        return ResponseEntity.ok(roleService.findById(id));
    }
    @GetMapping("/get-all-roles")
    @Operation(summary = "Получить все роли")
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        return ResponseEntity.ok(RolesMapper.roleEntitySetToDTOSet(roleService.findAll()));
    }
}