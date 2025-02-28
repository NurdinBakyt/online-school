package org.nurdin.school.service;

import org.nurdin.school.dto.news.NewsDto;
import org.nurdin.school.dto.news.NewsImage;
import org.nurdin.school.dto.request.NewsCreateDTO;
import org.nurdin.school.dto.request.NewsUpdateDTO;
import org.nurdin.school.entity.NewsEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface NewsService {
    NewsEntity addNews(NewsCreateDTO newsDto, MultipartFile imageFile) throws IOException;
    NewsEntity addNewsNotImage(NewsCreateDTO newsDto);
    List<NewsDto> getAllNews(Integer offset, Integer limit);
    NewsEntity getNewsById(Long id);
    NewsEntity updateNews(NewsUpdateDTO newsDto, MultipartFile imageFile) throws IOException;
    NewsEntity updateNewsNotImage(NewsUpdateDTO newsDto);
    void deleteNews(String id);
    void uploadImage(Long id, NewsImage newsImage);
}
