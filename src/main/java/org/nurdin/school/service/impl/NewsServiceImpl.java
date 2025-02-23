package org.nurdin.school.service.impl;

import org.nurdin.school.exceptions.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.nurdin.school.dto.NewsDto;
import org.nurdin.school.dto.RoleDTO;
import org.nurdin.school.dto.request.NewsCreateDTO;
import org.nurdin.school.dto.request.NewsUpdateDTO;
import org.nurdin.school.dto.response.UserDtoResponse;
import org.nurdin.school.entity.NewsEntity;
import org.nurdin.school.entity.UserEntity;
import org.nurdin.school.repository.NewsRepository;
import org.nurdin.school.repository.UserRepository;
import org.nurdin.school.service.NewsService;
import org.nurdin.school.util.NewsDtoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {

    private final UserRepository userRepository;
    private final NewsRepository newsRepository;
    private final NewsDtoMapper newsDtoMapper;

    @Autowired
    public NewsServiceImpl(
        NewsRepository newsRepository,
        UserRepository userRepository,
        NewsDtoMapper newsDtoMapper
    ) {
        this.userRepository = userRepository;
        this.newsRepository = newsRepository;
        this.newsDtoMapper = newsDtoMapper;
    }

    @Override
    public NewsEntity addNews(NewsCreateDTO newsDto, MultipartFile imageFile) throws IOException {
        UserEntity author = userRepository.findByUsername(newsDto.getUsername())
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
        NewsEntity news = newsDtoMapper.newsСreateDTOToEntity(newsDto, author);
        // news.setImageName(imageFile.getOriginalFilename());
        // news.setImageType(imageFile.getContentType());
        // news.setImageDate(imageFile.getBytes());
        return newsRepository.save(news);
    }

    @Override
    public NewsEntity addNewsNotImage(NewsCreateDTO newsDto) {
        UserEntity author = userRepository.findByUsername(newsDto.getUsername())
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));
        NewsEntity news = newsDtoMapper.newsСreateDTOToEntity(newsDto, author);
        return newsRepository.save(news);
    }

    @Override
    public List<NewsDto> getAllNews(Integer offset, Integer limit) {
        PageRequest pageable = PageRequest.of(offset, limit);
        Page<NewsEntity> newsEntities = newsRepository.findAll(pageable);
        return newsEntities.getContent().stream().map(entity -> {
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

    @Override
    public NewsEntity getNewsById(Long id) {
        Optional<NewsEntity> news = newsRepository.findById(id);
        return news.orElseThrow(() -> new RuntimeException("News not found with id: " + id));
    }

    @Override
    public NewsEntity updateNews(NewsUpdateDTO newsDto, MultipartFile imageFile) throws IOException {
        if (newsDto.getId() == null || !newsRepository.existsById(newsDto.getId())) {
            throw new RuntimeException("News not found with id: " + newsDto.getId());
        }
        NewsEntity news = newsDtoMapper.newsUpdateDTOToEntity(newsDto);
        // news.setImageName(imageFile.getOriginalFilename());
        // news.setImageType(imageFile.getContentType());
        // news.setImageDate(imageFile.getBytes());
        return newsRepository.save(news);
    }

    @Override
    public NewsEntity updateNewsNotImage(NewsUpdateDTO newsDto) {
        if (newsDto.getId() == null || !newsRepository.existsById(newsDto.getId())) {
            throw new RuntimeException("News not found with id: " + newsDto.getId());
        }
        NewsEntity newsUpdate = newsRepository.findById(newsDto.getId()).orElse(null);
        newsUpdate.setTitle(newsDto.getNewsTitle());
        newsUpdate.setContent(newsDto.getNewsContent());
        return newsRepository.save(newsUpdate);
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
