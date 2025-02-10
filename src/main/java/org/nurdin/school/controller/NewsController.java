package org.nurdin.school.controller;

import org.nurdin.school.dto.NewsDto;
import org.nurdin.school.dto.request.NewsCreateDTO;
import org.nurdin.school.dto.request.NewsUpdateDTO;
import org.nurdin.school.entity.NewsEntity;
import org.nurdin.school.exceptions.NewNotFoundException;
import org.nurdin.school.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/news")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @PreAuthorize("hasAnyAuthority(\"DIRECTOR\", \"SECRETARY\", \"HEAD_TEACHER\")")
    @PostMapping("/addNews")
    public ResponseEntity<?> addNews(
        @RequestPart NewsCreateDTO newsDto, 
        @RequestPart MultipartFile imageFile
    ) {
        try {
            NewsEntity newsEntity = newsService.addNews(newsDto, imageFile);
            URI location = URI.create("/api/v1/news/detAllNews");
            return ResponseEntity.created(location).body(newsEntity);
        } catch(Exception e) {
            throw new NewNotFoundException(e.getMessage());
        }
        
    }
    
    
    @GetMapping("/detAllNews")
    public List<NewsDto> getAllNews(
        @RequestParam(
            value = "offset",
            defaultValue = "0"
        ) Integer offset,
        @RequestParam(
            value = "limit",
            defaultValue = "10"
        ) Integer limit
    ) {
        return newsService.getAllNews(offset, limit);
    }

    @GetMapping("/getNewsById")
    public ResponseEntity<?> getNewsById(@PathVariable Long id) {
        try {
            NewsEntity news = newsService.getNewsById(id);
            return ResponseEntity.ok(news);
        } catch(RuntimeException e) {
            throw new NewNotFoundException(e.getMessage());
        }
    }

    @PutMapping("/updateNews")
    public ResponseEntity<?> updateNews(
        @RequestPart NewsUpdateDTO newsDto,
        @RequestPart MultipartFile imageFile
    ) {
        try {
            NewsEntity newsEntity = newsService.updateNews(newsDto, imageFile);
            return ResponseEntity.ok(newsEntity);
        } catch(Exception e) {
            throw new NewNotFoundException(e.getMessage());
        }
    }

    @DeleteMapping("/deleteNews")
    public void deleteNews(@PathVariable String id) {
        newsService.deleteNews(id);
    }
}
