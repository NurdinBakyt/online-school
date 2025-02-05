package org.nurdin.school.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.nurdin.school.dto.RoleDTO;
import org.nurdin.school.enums.UserStatus;

import java.time.LocalDateTime;
import java.util.Set;

@Schema(description = "ДТОшка для ответа, с некоторыми отсутствующими полями, нужен для ответа")
public class UserDtoResponse {
    private Long id;
    private String email;
    @JsonProperty("username")
    private String username;
    private Set<RoleDTO> roles;
    @Schema(description = "дата добавления")
    private LocalDateTime createdAt;
    @Schema(description = "это текущий статус аккаунта пользователя, активный, удалённый или заблокированный")
    private UserStatus userStatus;

    // Конструктор с параметрами
    public UserDtoResponse(Long id, Set<RoleDTO> roles) {
        this.id = id;
        this.roles = roles;
    }

    // Пустой конструктор (нужен для Spring, Jackson и т.д.)
    public UserDtoResponse() {
    }

    public UserDtoResponse(Long id, String roleTitle) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Set<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleDTO> roles) {
        this.roles = roles;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt() {
        this.createdAt = LocalDateTime.now();
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "UserDtoResponse{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", roles=" + roles +
                ", createdAt=" + createdAt +
                ", userStatus=" + userStatus +
                '}';
    }
}