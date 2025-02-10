package org.nurdin.school.service;

import org.nurdin.school.entity.NewsEntity;

import java.util.List;

public interface NewsService {
    NewsEntity addNews(NewsEntity news);
    List<NewsEntity> getAllNews();
    NewsEntity getNewsById(Long id);
    void updateNews(NewsEntity news);
    void deleteNews(String id);
}
