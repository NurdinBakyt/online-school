package org.nurdin.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "roles")
@Schema(description = "")
public class RoleEntity extends BaseEntity {
    private String title;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<UserEntity> users;

    public RoleEntity(Long id,String title) {
        this.title = title;
        this.id = id;
    }
    public RoleEntity(String title) {
        this.title = title;
    }

    public RoleEntity() {
    }

    public String getTitle() {
        return title;
    }

    public RoleEntity setTitle(String roleTitle) {
        this.title = roleTitle;
        return this;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "RoleEntity{" +
                "roleTitle='" + title + '\'' +
                ", users=" + users +
                ", id=" + id +
                '}';
    }
}
