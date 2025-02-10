package org.nurdin.school.util;

import org.nurdin.school.dto.NewsCreateDTO;
import org.nurdin.school.entity.NewsEntity;
import org.nurdin.school.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class NewsCreateDtoMapper {
    // Маппинг Entity -> DTO
    public NewsCreateDTO newsEntityToDTO(NewsEntity newsEntity) {
        NewsCreateDTO newsDto = new NewsCreateDTO();
        newsDto.setNewsContent(newsEntity.getContent());
        newsDto.setNewsTitle(newsEntity.getTitle());
        newsDto.setDateTime(newsEntity.getDate());
        newsDto.setUsername(newsEntity.getAuthor().getUsername());
        return newsDto;
    }

    // Маппинг DTO -> Entity
    public NewsEntity newsDTOToEntity(NewsCreateDTO newsDto, UserEntity author) {
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setContent(newsDto.getNewsContent());
        newsEntity.setTitle(newsDto.getNewsTitle());
        newsEntity.setDate(newsDto.getDateTime());
        newsEntity.setAuthor(author);
        return newsEntity;
    }

}
