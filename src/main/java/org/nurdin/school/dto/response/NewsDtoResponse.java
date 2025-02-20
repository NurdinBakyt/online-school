package org.nurdin.school.dto.response;

public class NewsDtoResponse {

            /*
        1) Тема
        2) текст новостей
        3) фото[]
        4) автор
         */
    private Long id;
    private String newsTitle;
    private String newsContent;
    private String photo;
    private UserDtoResponse user;

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsContent() {
        return newsContent;
    }

    public void setNewsContent(String newsContent) {
        this.newsContent = newsContent;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public UserDtoResponse getUser() {
        return user;
    }

    public void setUser(UserDtoResponse user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String   toString() {
        return "NewsDtoResponse{" +
                "id=" + id +
                ", newsTitle='" + newsTitle + '\'' +
                ", newsContent='" + newsContent + '\'' +
                ", photo='" + photo + '\'' +
                ", user=" + user +
                '}';
    }
}
