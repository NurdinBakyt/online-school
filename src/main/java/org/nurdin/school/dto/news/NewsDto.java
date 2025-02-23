package org.nurdin.school.dto.news;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.nurdin.school.dto.response.UserDtoResponse;

import java.time.LocalDateTime;
import java.util.List;

public class NewsDto {
    private Long id;
    private String newsTitle;
    private String newsContent;
    private String photo;
    private UserDtoResponse author;
    private LocalDateTime dateTime;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<String> images;

    public UserDtoResponse getAuthor() {
        return author;
    }

    public void setAuthor(UserDtoResponse author) {
        this.author = author;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "NewsDto{" +
            "id=" + id +
            ", newsTitle='" + newsTitle + '\'' +
            ", newsContent='" + newsContent + '\'' +
            ", photo='" + photo + '\'' +
            ", author=" + author +
            ", dateTime=" + dateTime +
            ", images=" + images +
            '}';
    }
}
