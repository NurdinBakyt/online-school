package org.nurdin.school.service;

import org.nurdin.school.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserEntity register(UserEntity user) ;
    Optional<UserEntity> findById(Long id);
    UserEntity findByUsername(String username);
    UserEntity findByEmail(String email);
    UserEntity updateUsername(String username, String newusername);
    UserEntity updateUserPassword(String password, String newPassword);
    UserEntity deleteUser(Long id);
    UserEntity deleteUserByName(String username);
    UserEntity deleteUserByEmail(String email);
    List<UserEntity> getAllUsers();
}
