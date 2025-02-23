package org.nurdin.school.repository;

import jakarta.transaction.Transactional;
import org.nurdin.school.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> getByTitle(String title);

    boolean existsByTitle(String title);

    void deleteByTitle(String title);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO m2m_users_roles (user_id, role_id) " +
            "SELECT :userId, r.id FROM roles r WHERE r.title = :roleTitle", nativeQuery = true)
    void updateByRoleTitle(@Param("roleTitle") String roleTitle, @Param("userId") Long userId);

    @Transactional
    @Query(value = "DELETE FROM m2m_users_roles WHERE user_id = :userId AND role_id = :roleId", nativeQuery = true)
    void removeRoleFromUser(@Param("userId") Long userId, @Param("roleId") Long roleId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM m2m_users_roles ur WHERE ur.user.id = :userId AND ur.role.title = :roleTitle",nativeQuery = true)
    void deleteRoleFromUserByTitle(@Param("userId") Long userId, @Param("roleTitle") String roleTitle);
}