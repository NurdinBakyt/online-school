package org.nurdin.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.nurdin.school.dto.UserDTO;
import org.nurdin.school.dto.response.UserDtoResponse;
import org.nurdin.school.entity.RoleEntity;
import org.nurdin.school.entity.UserEntity;
import org.nurdin.school.exceptions.RoleNotFoundException;
import org.nurdin.school.exceptions.UserNotFoundException;
import org.nurdin.school.repository.RoleRepository;
import org.nurdin.school.service.RoleService;
import org.nurdin.school.service.UserService;
import org.nurdin.school.util.UserDTOMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Operation(summary = "Endpoint который ищет user по id")
    public ResponseEntity<UserDtoResponse> getUserById(@RequestParam Long id) {
        return ResponseEntity.ok(UserDTOMapper.userEntityToDTOResponse(userService.findById(id).orElse(null)));
    }

    @Operation(summary = "Endpoint который ищет user по username")
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


    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @Operation(summary = "Метод обновление имени у пользователя. Он имеет 2 параметра.1:старый username.2:новый username.", description = "К этому методу имеют доступ пользователи только с ролью ADMIN,USER")
    @PutMapping("/update-user-name")
    public ResponseEntity<UserDtoResponse> updateUsername(@RequestParam String username, @RequestParam String newUsername) {
        UserDtoResponse user = UserDTOMapper.userEntityToDTOResponse(userService.updateUsername(username, newUsername));
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Это метод обновления пароля. 1 параметр:пароль текущий пользователя.2:новый пароль.", description = "Имеют доступ только USER,ADMIN")
    @PreAuthorize("hasAnyAuthority('USER','ADMIN')")
    @PutMapping("/update-user-password")
    public ResponseEntity<UserDtoResponse> updatePassword(@RequestParam String password, @RequestParam String newPassword) {
        UserDtoResponse user = UserDTOMapper.userEntityToDTOResponse(userService.updateUserPassword(password, newPassword));
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Этот метод удаляет пользователя по ID.", description = "К этому методу имеют доступ только DIRECTOR,ADMIN")
    @PreAuthorize(value = "hasAnyAuthority('DIRECTOR','ADMIN')")
    @DeleteMapping("/delete-user-by-id/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
        UserEntity user = userService.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь с ID " + id + " не найден"));

        userService.deleteUser(id);
        return ResponseEntity.ok("Пользователь " + user.getUsername() + " удален");
    }

    @PreAuthorize(value = "hasAnyAuthority('DIRECTOR','ADMIN')")
    @DeleteMapping("/delete-user-by-name")
    @Operation(summary = "Этот метод удаляет пользователя по USERNAME.", description = "К этому методу имеют доступ только DIRECTOR,ADMIN")
    public ResponseEntity<String> deleteUserByName(@RequestParam String username) {
        UserEntity user = userService.deleteUserByName(username);
        if (user == null){
            throw new UserNotFoundException("Такой пользователь не найден");
        }
        return ResponseEntity.ok("Пользователь удален");
    }

    @Operation(summary = "Этот метод удаляет пользователя по EMAIL.", description = " этому методу имеют доступ только DIRECTOR,ADMIN")
    @PreAuthorize(value = "hasAnyAuthority('DIRECTOR','ADMIN')")
    @DeleteMapping("/delete-user-by-email")
    public ResponseEntity<String> deleteUserByEmail(@RequestParam String email) {
        UserEntity user = userService.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("Такой пользователь не найден");
        }
        userService.deleteUserByEmail(email);
        return ResponseEntity.ok("Пользователь удален");
    }

    @PreAuthorize(value = "hasAnyAuthority('ADMIN','DIRECTOR')")
    @PostMapping("/add-role-to-user-by-title")
    @Operation(summary = "Добавление роли по названию.Тоесть мы пишем роль в объекте ее название.", description = "роль добавляется пользователю.\" +\n" +
            "            \"Доступ имеют только ADMIN,DIRECTOR")
    public ResponseEntity<String> addRoleByTitle(@RequestParam String title, @RequestParam Long user_id) {
        UserEntity user = userService.findById(user_id)
                .orElseThrow(() -> new UserNotFoundException("Пользователь с ID " + user_id + " не найден"));

        roleService.addRoleToUserByTitle(title, user_id);
        return ResponseEntity.ok("Роль '" + title + "' добавлена пользователю: " + user.getUsername());
    }

    @PreAuthorize(value = "hasAnyAuthority('DIRECTOR','ADMIN')")
    @DeleteMapping("/delete-user-role-by-id")
    @Operation(summary = "Удаление роли пользователя", description = "Работает по id роли, то есть удаляется не пользователь а именно его роль, данные пользователя должны остаться.2 параметра. 1:id пользователя,2:id роли Доступ имеют только DIRECTOR")
    public ResponseEntity<String> deleteUserRole(@RequestParam Long user_id, @RequestParam Long role_id) {
        Optional<UserEntity> userDTO = userService.findById(user_id);
        if (userDTO.isEmpty()) {
            throw new UserNotFoundException("Пользователь не найден");
        }
        Optional<RoleEntity> roleDTO = roleRepository.findById(role_id);
        if (roleDTO.isEmpty()) {
            throw new RoleNotFoundException("Роль не найдена");
        }

        roleService.delete(user_id, role_id);
        return ResponseEntity.ok("Удалена роль " + roleDTO.get().getTitle() + " ,Пользователя: " + userDTO.get().getUsername());
    }

    @DeleteMapping("/delete-user-role-by-title")
    @Operation(summary = "Удаляет роль у пользователя по название роли. 2 параметра 1:название роли,2:id пользователя")
    public ResponseEntity<String> deleteUserRoleByTitle(@RequestParam String title, @RequestParam Long user_id) throws Exception {
        Optional<UserEntity> userDTO = userService.findById(user_id);
        if (userDTO.isEmpty()) {
            throw new UserNotFoundException("Пользователь с таким id не найден");
        }
        Optional<RoleEntity> role = roleRepository.getByTitle(title);
        if (role.isEmpty()) {
            throw new RoleNotFoundException("Пользователь с такой ролью не найден");
        }

        roleService.deleteByTitle(user_id, title);
        return ResponseEntity.ok("Удалена роль " + role.get().getTitle() + " ,Пользователя: " + userDTO.get().getUsername());
    }
}