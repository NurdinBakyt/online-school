package org.nurdin.school.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import org.nurdin.school.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.Set;

@Schema(description = "ДТО для пользователей со всеми его полями")
public class UserDTO {
    @NotBlank(message = "Email не должен быть пустым")
    @Email(message = "Пожалуйста введите корректный email")
    private String email;

    @Size(min = 5, max = 50, message = "Username должен содержать от 6 до 50 символов")
    @NotBlank(message = "username не должен быть пустым")
    @Pattern(regexp = "^[a-zA-Z0-9._-]{5,60}$", message = "Username должен содержать только латинские буквы, цифры, точки, дефисы или подчеркивания")
    private String username;

    @Size(min = 6, max = 50, message = "Пароль должен содержать от 6 до 50 символов")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)[a-z\\d]{6,50}$",
        message = "Пароль должен содержать хотя бы одну строчную букву и одну цифру, без спецсимволов и заглавных букв")
    private String password;

    private Set<RoleDTO> roles;
    private UserStatus userStatus;

    @Schema(description = "Дата создания пользователя")
    private LocalDateTime createdAt;

    // Геттеры и сеттеры


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }


    @Override
    public String toString() {
        return "UserDTO{" +
            ", email='" + email + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", roles=" + roles +
            ", userStatus=" + userStatus +
            ", createdAt=" + createdAt +
            '}';
    }
}
