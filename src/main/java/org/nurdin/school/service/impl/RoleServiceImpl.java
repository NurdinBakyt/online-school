package org.nurdin.school.service.impl;

import jakarta.transaction.Transactional;
import org.nurdin.school.entity.RoleEntity;
import org.nurdin.school.entity.UserEntity;
import org.nurdin.school.exceptions.RoleNotFoundException;
import org.nurdin.school.exceptions.UserNotFoundException;
import org.nurdin.school.repository.RoleRepository;
import org.nurdin.school.repository.UserRepository;
import org.nurdin.school.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public RoleServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public RoleEntity findById(Long id) {
        return roleRepository.findById(id)
                .orElseThrow(() -> new RoleNotFoundException("Роль с id: " + " не найдена"));
    }

    @Override
    public List<RoleEntity> findAll() {
        List<RoleEntity> roles = roleRepository.findAll();
        if (roles.isEmpty()) {
            throw new RoleNotFoundException("Роли не найдены");
        }
        return roles;
    }

    @Override
    public void addRoleToUserByTitle(String roleName, Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException("Пользователь с таким id не найден");
        }
        if (!roleRepository.existsByTitle(roleName)){
            throw new RoleNotFoundException("Роль не найдена");
        }
        roleRepository.updateByRoleTitle(roleName, userId);
    }

    @Override
    public RoleEntity save(RoleEntity role) {
        return roleRepository.save(role);
    }

    @Override
    @Transactional
    public void delete(Long user_id, Long role_id) {
        if (!userRepository.existsById(user_id)) {
            throw new UserNotFoundException("Пользователь с таким id не найден");
        }
        if (!roleRepository.existsById(role_id)) {
            throw new RoleNotFoundException("Роль не найдена");
        }
        roleRepository.removeRoleFromUser(user_id, role_id);
    }

    @Override
    public void deleteByTitle(Long user_id,String roleName) {
        roleRepository.deleteRoleFromUserByTitle(user_id,roleName);
    }

    @Override
    public void deleteByRoleId(Long roleId) {
        if (!roleRepository.existsById(roleId)) {
            throw new RoleNotFoundException("Роль не найдена");
        }
        roleRepository.deleteById(roleId);
    }

    @Override
    public RoleEntity addNewRole(RoleEntity role) {
        return roleRepository.save(role);
    }
}
