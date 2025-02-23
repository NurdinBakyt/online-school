package org.nurdin.school.util.mappers.impl;

import org.nurdin.school.dto.news.NewsImage;
import org.nurdin.school.dto.news.NewsImageDTO;
import org.nurdin.school.util.Mappable;
import org.nurdin.school.util.mappers.NewsImageMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NewsImageMapperImpl implements NewsImageMapper {
    @Override
    public NewsImageDTO toDTO(NewsImage entity) {
        if (entity == null) {
            return null;
        }
        NewsImageDTO dto = new NewsImageDTO();
        dto.setImage(entity.getMultipartFile());
        return dto;
    }

    @Override
    public NewsImage toEntity(NewsImageDTO dto) {
        if (dto == null) {
            return null;
        }
        NewsImage newsImage = new NewsImage();
        newsImage.setMultipartFile(dto.getImage());
        return newsImage;
    }

    @Override
    public List<NewsImageDTO> toDTO(List<NewsImage> entities) {
        if (entities == null) {
            return null;
        }
        return entities.stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    @Override
    public List<NewsImage> toEntity(List<NewsImageDTO> dtos) {
        if (dtos == null) {
            return null;
        }
        return dtos.stream()
            .map(this::toEntity)
            .collect(Collectors.toList());
    }
}
