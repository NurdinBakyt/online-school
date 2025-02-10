package org.nurdin.school.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ДТОшка ролей")
public class RoleDTO {
    @Schema(description = "это ID")
    private Long id;
    @Schema(description = "это звание")
    private String title;

    public RoleDTO(Long id, String title) {
        this.id = id;
        this.title = title;
    }
    public RoleDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}