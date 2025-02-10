package org.nurdin.school.controller;

import org.nurdin.school.entity.NewsEntity;
import org.nurdin.school.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/news")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PostMapping("/addNews")
    public NewsEntity addNews(@RequestBody NewsEntity news) {
        return newsService.addNews(news);
    }

    @GetMapping("/detAllNews")
    public List<NewsEntity> getAllNews() {
        return newsService.getAllNews();
    }

    @GetMapping("/getNewsById")
    public NewsEntity getNewsById(@PathVariable Long id) {
        return newsService.getNewsById(id);
    }

    @PutMapping("/updateNews")
    public void updateNews(@RequestBody NewsEntity news) {
        newsService.updateNews(news);
    }

    @DeleteMapping("/deleteNews")
    public void deleteNews(@PathVariable String id) {
        newsService.deleteNews(id);
    }
}
