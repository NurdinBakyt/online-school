package org.nurdin.school.controller;

import org.nurdin.school.dto.NewsDto;
import org.nurdin.school.dto.RoleDTO;
import org.nurdin.school.dto.response.UserDtoResponse;
import org.nurdin.school.entity.NewsEntity;
import org.nurdin.school.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public List<NewsDto> getAllNews() {
        List<NewsEntity> newsEntities = newsService.getAllNews();
        return newsEntities.stream().map(entity -> {
            NewsDto dto = new NewsDto();
            Set<RoleDTO> roleDTOSet = entity.getAuthor().getRoles().stream()
                .map(role -> new RoleDTO(role.getId(), role.getTitle()))
                .collect(Collectors.toSet());

            UserDtoResponse userDtoResponse = new UserDtoResponse(
                entity.getAuthor().getId(),
                roleDTOSet
            );
            dto.setId(entity.getId());
            dto.setNewsTitle(entity.getTitle());
            dto.setNewsContent(entity.getContent());
            dto.setDateTime(entity.getDate());
            dto.setAuthor(userDtoResponse);
            return dto;
        }).toList();
    }

    @GetMapping("/getNewsById")
    public ResponseEntity<?> getNewsById(@PathVariable Long id) {
        try {
            NewsEntity news = newsService.getNewsById(id);
            return ResponseEntity.ok(news);
        } catch(RuntimeException e) {
            return ResponseEntity.status(404).body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/updateNews")
    public ResponseEntity<String> updateNews(@RequestBody NewsEntity news) {
        newsService.updateNews(news);
        return ResponseEntity.ok("Новость успешно обновлена");
    }

    @DeleteMapping("/deleteNews")
    public void deleteNews(@PathVariable String id) {
        newsService.deleteNews(id);
    }
}
