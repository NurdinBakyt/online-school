package org.nurdin.school.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.nurdin.school.dto.news.NewsDto;
import org.nurdin.school.dto.news.NewsImage;
import org.nurdin.school.dto.news.NewsImageDTO;
import org.nurdin.school.dto.request.NewsCreateDTO;
import org.nurdin.school.dto.request.NewsUpdateDTO;
import org.nurdin.school.entity.NewsEntity;
import org.nurdin.school.exceptions.NewNotFoundException;
import org.nurdin.school.service.NewsService;
import org.nurdin.school.util.mappers.NewsDtoMapper;
import org.nurdin.school.util.mappers.NewsImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/v1/news")
public class NewsController {

    private final NewsService newsService;
    private final NewsDtoMapper newsDtoMapper;
    private final NewsImageMapper newsImageMapper;

    @Autowired
    public NewsController(NewsService newsService, NewsDtoMapper newsDtoMapper, NewsImageMapper newsImageMapper) {
        this.newsService = newsService;
        this.newsDtoMapper = newsDtoMapper;
        this.newsImageMapper = newsImageMapper;
    }

    // @PreAuthorize("hasAnyAuthority(\"DIRECTOR\", \"SECRETARY\", \"HEAD_TEACHER\")")
    @PostMapping("/addNews")
    public ResponseEntity<?> addNews(
        @RequestBody NewsCreateDTO newsDto
        // @RequestPart MultipartFile imageFile
    ) {
        try {
            NewsEntity newsEntity = newsService.addNewsNotImage(newsDto);
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

    // @PreAuthorize("hasAnyAuthority(\"DIRECTOR\", \"SECRETARY\", \"HEAD_TEACHER\")")
    @PutMapping("/updateNews")
    public ResponseEntity<?> updateNews(
        @RequestBody NewsUpdateDTO newsDto
        // @RequestPart MultipartFile imageFile
    ) {
        try {
            NewsEntity newsEntity = newsService.updateNewsNotImage(newsDto);
            return ResponseEntity.ok(newsEntity);
        } catch(Exception e) {
            throw new NewNotFoundException(e.getMessage());
        }
    }

    @PreAuthorize("hasAnyAuthority(\"DIRECTOR\", \"SECRETARY\", \"HEAD_TEACHER\")")
    @DeleteMapping("/deleteNews")
    public ResponseEntity<String> deleteNews(@PathVariable String id) {
        try {
            newsService.deleteNews(id);
            return ResponseEntity.ok(
                String.format("Новость c id:№%d удалена успешно!", id)
            );
        } catch(RuntimeException e) {
            throw new NewNotFoundException(e.getMessage());
        }
    }
   @PostMapping("{id}/image")
   @Operation(method = "enpoint который добавляет картинки,в сущность новостей,в массив images")
   public ResponseEntity<?> addImage(@PathVariable Long id,
                                     @Validated @ModelAttribute NewsImageDTO newsDto) {
       NewsImage newsImage = newsImageMapper.toEntity(newsDto);
       newsService.uploadImage(id,newsImage);
       return ResponseEntity.ok("Картрика успешно сохранена");
   }
}
