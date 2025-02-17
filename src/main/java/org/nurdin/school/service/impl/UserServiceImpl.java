package org.nurdin.school.service.impl;

import jakarta.transaction.Transactional;
import org.nurdin.school.entity.RoleEntity;
import org.nurdin.school.entity.UserEntity;
import org.nurdin.school.exceptions.RoleNotFoundException;
import org.nurdin.school.exceptions.UserExistsException;
import org.nurdin.school.exceptions.UserNotFoundException;
import org.nurdin.school.repository.RoleRepository;
import org.nurdin.school.repository.UserRepository;
import org.nurdin.school.service.AuthService;
import org.nurdin.school.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthService authService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authService = authService;
    }

    @Override
    public UserEntity register(UserEntity user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()
                || user.getPassword() == null || user.getPassword().trim().isEmpty()
                || user.getEmail() == null || user.getEmail().trim().isEmpty()
        ) {
            throw new IllegalArgumentException("Данные не должны быть пустыми!");
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserNotFoundException("Пользователь с таким email уже существует");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new UserNotFoundException("Пользователь с таким username уже существует");
        }

        if (user.getPassword() == null) {
            throw new IllegalArgumentException("Пароль не может быть пустым");
        }

        Set<RoleEntity> roles = user.getRoles() == null ? new HashSet<>() :
                user.getRoles()
                        .stream()
                        .map(x -> roleRepository.getByTitle(x.getTitle())
                                .orElseThrow(() -> new RoleNotFoundException("Роль не найдена")))
                        .collect(Collectors.toSet());
        user.setRoles(roles);

        user.setEnabled(false);
        user.setVerificationCode(authService.generateVerificationCode());
        user.setVerificationCodeExpiration(LocalDateTime.now().plusMinutes(15));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        System.out.println("Перед сохранением: " + user);
        UserEntity savedUser = userRepository.save(user);
        System.out.println("После сохранения: " + savedUser);

        try {
            authService.sendVerificationEmail(savedUser);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при отправке email: {}");
        }

        return savedUser;
    }


    @Override
    public Optional<UserEntity> findById(Long id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("Такой пользователь не найден");
        }
        return user;
    }

    @Override
    public UserEntity findByUsername(String name) {
        return userRepository.findByUsername(name)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
    }

    @Override
    public UserEntity updateUsername(String username, String newUsername) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
        user.setUsername(newUsername);
        return userRepository.save(user);
    }

    @Override
    public UserEntity updateUserPassword(String password, String newPassword) {
        UserEntity user = userRepository.findByPassword(password);
        user.setPassword(newPassword);
        return userRepository.save(user);
    }

    @Override
    public UserEntity deleteUser(Long id) {
        return deleteUserEntity(userRepository.findById(id).orElse(null));
    }

    @Override
    public UserEntity deleteUserByName(String username) {
        return deleteUserEntity(userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("Пользователь не найден")));
    }

    @Override
    public UserEntity deleteUserByEmail(String email) {
        return deleteUserEntity(userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Пользователь не найден")));
    }

    private UserEntity deleteUserEntity(UserEntity user) {
        if (user != null) {
            userRepository.delete(user);
        }
        return user;
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepository.findAll();
    }
}