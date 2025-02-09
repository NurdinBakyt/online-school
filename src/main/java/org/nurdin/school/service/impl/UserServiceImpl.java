package org.nurdin.school.service.impl;

import org.nurdin.school.entity.RoleEntity;
import org.nurdin.school.entity.UserEntity;
import org.nurdin.school.exceptions.UserNotFoundException;
import org.nurdin.school.repository.RoleRepository;
import org.nurdin.school.repository.UserRepository;
import org.nurdin.school.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity register(UserEntity user) {
        Set<RoleEntity> roles = user.getRoles()
                .stream()
                .map(x -> roleRepository.getByTitle(x.getTitle())
                        .orElseThrow(() -> new RuntimeException("Роль не найдена")))
                .collect(Collectors.toSet());
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
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
        UserEntity user = userRepository.findByUsername(name);
        if (user == null){
            throw new UserNotFoundException("Такой пользователь не найден");
        }
        return user;
    }

    @Override
    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public UserEntity updateUsername(String username, String newUsername) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("Такой пользователь не найден");
        }
        user.setUsername(newUsername);
        return userRepository.save(user);
    }

    @Override
    public UserEntity updateUserPassword(String password, String newPassword) {
        UserEntity user = userRepository.findByPassword(password);
        if(user == null){
            throw new UserNotFoundException("Неверный пароль");
        }
        user.setPassword(newPassword);
        return userRepository.save(user);
    }

    @Override
    public UserEntity deleteUser(Long id) {
        return deleteUserEntity(userRepository.findById(id).orElse(null));
    }

    @Override
    public UserEntity deleteUserByName(String username) {
        return deleteUserEntity(userRepository.findByUsername(username));
    }

    @Override
    public UserEntity deleteUserByEmail(String email) {
        return deleteUserEntity(userRepository.findByEmail(email));
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