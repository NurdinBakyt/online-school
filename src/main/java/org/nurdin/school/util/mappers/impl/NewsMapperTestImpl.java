package org.nurdin.school.util.mappers.impl;

import org.nurdin.school.dto.news.NewsDto;
import org.nurdin.school.entity.NewsEntity;
import org.nurdin.school.util.mappers.NewsMapperTest;
import org.nurdin.school.util.mappers.UserDTOMapper;

import java.util.List;
import java.util.stream.Collectors;

public class NewsMapperTestImpl implements NewsMapperTest {

    private final UserDTOMapper userMapper;

    public NewsMapperTestImpl(UserDTOMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public NewsDto toDTO(NewsEntity entity) {
        if (entity == null) {
            return null;
        }

        NewsDto dto = new NewsDto();
        dto.setId(entity.getId());
        dto.setNewsTitle(entity.getTitle());
        dto.setNewsContent(entity.getContent());
        dto.setDateTime(entity.getDate());
        dto.setImages(entity.getImages());

        // Маппинг автора (UserEntity -> UserDtoResponse)
        if (entity.getAuthor() != null) {
            dto.setAuthor(UserDTOMapper.userEntityToDTOResponse(entity.getAuthor()));
        }

        return dto;
    }

    @Override
    public NewsEntity toEntity(NewsDto dto) {
        if (dto == null) {
            return null;
        }

        NewsEntity entity = new NewsEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getNewsTitle());
        entity.setContent(dto.getNewsContent());
        entity.setDate(dto.getDateTime());
        entity.setImages(dto.getImages());

        if (dto.getAuthor() != null) {
            entity.setAuthor(UserDTOMapper.responseToEntity(dto.getAuthor()));
        }

        return entity;
    }

    @Override
    public List<NewsDto> toDTO(List<NewsEntity> entities) {
        if (entities == null) {
            return List.of();
        }

        return entities.stream()
            .map(this::toDTO) // Используем метод toDTO для каждого элемента
            .collect(Collectors.toList());
    }

    @Override
    public List<NewsEntity> toEntity(List<NewsDto> dtos) {
        if (dtos == null) {
            return List.of();
        }

        return dtos.stream()
            .map(this::toEntity) // Используем метод toEntity для каждого элемента
            .collect(Collectors.toList());
    }
}
