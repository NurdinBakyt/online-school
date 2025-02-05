package org.nurdin.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.nurdin.school.dto.UserDTO;
import org.nurdin.school.dto.response.UserDtoResponse;
import org.nurdin.school.entity.RoleEntity;
import org.nurdin.school.entity.UserEntity;
import org.nurdin.school.repository.RoleRepository;
import org.nurdin.school.service.RoleService;
import org.nurdin.school.service.UserService;
import org.nurdin.school.util.UserDTOMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "api/v1/user")
public class UserController {
    private final UserService userService;
    private final UserDTOMapper userDTOMapper;
    private final RoleService roleService;
    private final RoleRepository roleRepository;


    public UserController(UserService userService, UserDTOMapper userDTOMapper, RoleService roleService, RoleRepository roleRepository) {
        this.userService = userService;
        this.userDTOMapper = userDTOMapper;
        this.roleService = roleService;
        this.roleRepository = roleRepository;
    }


    @PostMapping(value = "/register")
    @Operation(summary = "Метод для регистрации новых пользователей", description = "Как можно судить пр названию, метод для регистрации новых пользователей ")
    public ResponseEntity<UserDtoResponse> addUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(UserDTOMapper.userEntityToDTOResponse(
                userService.register(UserDTOMapper.userDTOtoEntity(userDTO))
        ));
    }

    @GetMapping("email")
    @Operation(summary = "Получение пользователей по email", description = " Исключительно поиск по email")
    public ResponseEntity<UserDtoResponse> getUserByEmail(@RequestParam String email) {
        return ResponseEntity.ok(UserDTOMapper.userEntityToDTOResponse(userService.findByEmail(email)));
    }

    @GetMapping("/get-by-id")
    public ResponseEntity<UserDtoResponse> getUserById(@RequestParam Long id) {
        return ResponseEntity.ok(UserDTOMapper.userEntityToDTOResponse(userService.findById(id).orElse(null)));
    }

    @GetMapping("/get-by-name")
    public ResponseEntity<UserDtoResponse> getUserName(@RequestParam String name) {
        return ResponseEntity.ok(UserDTOMapper.userEntityToDTOResponse(userService.findByUsername(name)));
    }

    @GetMapping(value = "/get-all-users")
    @Operation(summary = "Получение всех пользователей")
    public List<UserDtoResponse> getAllUsers() {
        List<UserEntity> users = userService.getAllUsers();
        return users.stream()
                .map(UserDTOMapper::userEntityToDTOResponse)
                .toList();
    }

    @PutMapping("/update-user-name")
    public ResponseEntity<UserDtoResponse> updateUsername(@RequestParam String username, @RequestParam String newUsername) {
        UserDtoResponse user = UserDTOMapper.userEntityToDTOResponse(userService.updateUsername(username, newUsername));
        return ResponseEntity.ok(user);
    }

    @PutMapping("/update-user-password")
    public ResponseEntity<UserDtoResponse> updatePassword(@RequestParam String email, @RequestParam String newPassword) {
        UserDtoResponse user = UserDTOMapper.userEntityToDTOResponse(userService.updateUserPassword(email, newPassword));
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/delete-user-by-id/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        Optional<UserEntity> userEntity = userService.findById(id);
        userService.deleteUser(id);
        return ResponseEntity.ok("Пользователь удален");
    }

    @DeleteMapping("/delete-user-by-name")
    public ResponseEntity<String> deleteUserByName(@RequestParam String username) {
        userService.deleteUserByName(username);
        return ResponseEntity.ok("Пользователь удален");
    }

    @DeleteMapping("/delete-user-by-email")
    public ResponseEntity<String> deleteUserByEmail(@RequestParam String email) {
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok("Пользователь удален");
    }


    @PostMapping("/add-role-to-user-by-title")
    @Operation(summary = "Добавление роли по названию ")
    public ResponseEntity<String> addRoleByTitle(@RequestParam String title, @RequestParam Long user_id) {
        UserEntity user = userService.findById(user_id)
                .orElseThrow(() -> new RuntimeException("Пользователь с ID " + user_id + " не найден"));

        roleService.addRoleToUserByTitle(title, user_id);
        return ResponseEntity.ok("Роль '" + title + "' добавлена пользователю: " + user.getUsername());
    }

    @DeleteMapping("/delete-user-role")
    @Operation(summary = "Удаление роли пользователя", description = "Работает по id роли, то есть удаляется не пользователь а именно его роль, данные пользователя должны остаться ")
    public ResponseEntity<String> deleteUserRole(@RequestParam Long user_id, @RequestParam Long role_id) {
        Optional<UserEntity> userDTO = userService.findById(user_id);
        Optional<RoleEntity> roleDTO = roleRepository.findById(role_id);

        roleService.delete(user_id, role_id);
        return ResponseEntity.ok("Удалена роль " + roleDTO.get().getTitle() + " ,Пользователя: " + userDTO.get().getUsername());
    }
}