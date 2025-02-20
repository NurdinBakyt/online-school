package org.nurdin.school.repository;

import org.nurdin.school.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByUsername(String username);
    UserEntity findByPassword(String password);
    Optional<UserEntity> findByVerificationCode(String verificationCode);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}