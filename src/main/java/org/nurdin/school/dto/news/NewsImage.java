package org.nurdin.school.dto.news;

import org.springframework.web.multipart.MultipartFile;

public class NewsImage {
    private MultipartFile multipartFile;


    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }
}
