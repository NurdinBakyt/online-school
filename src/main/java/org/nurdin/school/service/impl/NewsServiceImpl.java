package org.nurdin.school.service.impl;

import org.nurdin.school.entity.NewsEntity;
import org.nurdin.school.repository.NewsRepository;
import org.nurdin.school.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public NewsEntity addNews(NewsEntity news) {
        return newsRepository.save(news);
    }

    @Override
    public List<NewsEntity> getAllNews() {
        return newsRepository.findAll();
    }

    @Override
    public NewsEntity getNewsById(Long id) {
        Optional<NewsEntity> news = newsRepository.findById(id);
        return news.orElseThrow(() -> new RuntimeException("News not found with id: " + id));
    }

    @Override
    public void updateNews(NewsEntity news) {
        if (news.getId() == null || !newsRepository.existsById(news.getId())) {
            throw new RuntimeException("News not found with id: " + news.getId());
        }
        newsRepository.save(news);
    }

    @Override
    public void deleteNews(String id) {
        Long newsId = Long.parseLong(id); // Преобразуем строку в Long
        if (!newsRepository.existsById(newsId)) {
            throw new RuntimeException("News not found with id: " + id);
        }
        newsRepository.deleteById(newsId);
    }
}
