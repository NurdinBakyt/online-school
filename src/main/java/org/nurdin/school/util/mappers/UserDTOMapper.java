package org.nurdin.school.util.mappers;

import org.nurdin.school.dto.RoleDTO;
import org.nurdin.school.dto.UserDTO;
import org.nurdin.school.dto.response.UserDtoResponse;
import org.nurdin.school.entity.RoleEntity;
import org.nurdin.school.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class UserDTOMapper {
    //   этот метод,делает mapping userDto в responseDto. тоесть то что мы отдаем пользователю и здесь я не вывожу пароль ведь
    //   пользователю этого не нужно показывать.
    //   Чтобы в userController гибко выводить данные о user
    public static UserDtoResponse userDtoToResponse(UserDTO userDTO) {
        UserDtoResponse userDtoResponse = new UserDtoResponse();
        userDtoResponse.setUsername(userDTO.getUsername());
        userDtoResponse.setRoles(userDTO.getRoles());
        userDtoResponse.setEmail(userDTO.getEmail());
        userDtoResponse.setRoles(userDTO.getRoles().stream()
            .map(x -> new RoleDTO(x.getTitle()))
                .collect(Collectors.toSet()));
        userDtoResponse.setUserStatus(userDTO.getUserStatus());
        userDtoResponse.setCreatedAt();
        return userDtoResponse;
    }

    // Этот метод делает маппинг, DTO в entity
    public static UserDTO userEntityToDTO(UserEntity userEntity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userEntity.getUsername());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPassword(userEntity.getPassword());
        userDTO.setRoles(userEntity.getRoles().stream()
                .map(x -> new RoleDTO(x.getTitle()))
                .collect(Collectors.toSet()));
        userDTO.setUserStatus(userEntity.getUserStatus());
        userDTO.setCreatedAt();
        return userDTO;
    }

    // А этот метод наоборот Entity в DTO
    public static UserEntity userDTOtoEntity(UserDTO userDTO) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity.setRoles(userDTO.getRoles().stream()
                .map(x -> new RoleEntity(x.getTitle()))
                .collect(Collectors.toSet()));
        userEntity.setUserStatus(userDTO.getUserStatus());
        userEntity.setCreatedAt();
        return userEntity;
    }

    // А этот метод entity в dtoResponse
    public static UserDtoResponse userEntityToDTOResponse(UserEntity userEntity) {
        UserDtoResponse userDtoResponse = new UserDtoResponse();
        userDtoResponse.setId(userEntity.getId());
        userDtoResponse.setEmail(userEntity.getEmail());
        userDtoResponse.setUsername(userEntity.getUsername());
        userDtoResponse.setRoles(userEntity.getRoles().stream()
                .map(roleEntity -> new RoleDTO(roleEntity.getTitle()))
                .collect(Collectors.toSet()));
        userDtoResponse.setUserStatus(userEntity.getUserStatus());
        userDtoResponse.setEnabled(userEntity.isEnabled());
        userDtoResponse.setCreatedAt();
        return userDtoResponse;
    }
    public static UserEntity responseToEntity(UserDtoResponse userDtoResponse) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDtoResponse.getId());
        userEntity.setUsername(userDtoResponse.getUsername());
        userEntity.setEmail(userDtoResponse.getEmail());
        userDtoResponse.setEnabled(userDtoResponse.isEnabled());
        userDtoResponse.setRoles(userDtoResponse.getRoles().stream()
            .map(role -> new RoleDTO(role.getTitle()))
            .collect(Collectors.toSet()));
        userDtoResponse.setUserStatus(userDtoResponse.getUserStatus());
        userDtoResponse.setCreatedAt();
        return userEntity;
    }
}
