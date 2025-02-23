package org.nurdin.school.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "ДТОшка ролей")
public class RoleDTO {
    @Schema(description = "это звание")
    @NotBlank(message = "Роль не должна быть пустой!")
    private String title;

    public RoleDTO( String title) {
        this.title = title;
    }
    public RoleDTO() {
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
                ", title='" + title + '\'' +
                '}';
    }
}
