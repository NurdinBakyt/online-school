package org.nurdin.school.dto.news;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class NewsImageDTO {
    @NotNull(message = "Картинка не должна быть пустая")
    private MultipartFile image;

    public @NotNull(message = "Картинка не должна быть пустая") MultipartFile getImage() {
        return image;
    }

    public void setImage(@NotNull(message = "Картинка не должна быть пустая") MultipartFile image) {
        this.image = image;
    }
}
