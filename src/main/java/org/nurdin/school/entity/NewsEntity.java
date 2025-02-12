package org.nurdin.school.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "news")
public class NewsEntity extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    // private String imageName;
    // private String imageType;

    // @Lob
    // private byte[] imageDate;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    public NewsEntity() {}

    // public void setImageDate(byte[] imageDate) {
    //     this.imageDate = imageDate;
    // }

    // public byte[] getImageDate() {
    //     return imageDate;
    // }

    // public void setImageName(String imageName) {
    //     this.imageName = imageName;
    // }

    // public String getImageName() {
    //     return imageName;
    // }

    // public void setImageType(String imageType) {
    //     this.imageType = imageType;
    // }

    // public String getImageType() {
    //     return imageType;
    // }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getAuthor() {
        return author;
    }

    public void setAuthor(UserEntity author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "NewsEntity{" +
                "id=" + id +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date=" + date +
                '}';
    }
}
