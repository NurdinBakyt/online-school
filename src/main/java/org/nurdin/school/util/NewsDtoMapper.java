package org.nurdin.school.util;

import org.nurdin.school.dto.NewsDto;
import org.nurdin.school.dto.response.NewsDtoResponse;
import org.nurdin.school.entity.NewsEntity;
import org.springframework.stereotype.Service;

@Service
public class NewsDtoMapper {

    // Маппинг Entity -> DTO
    public static NewsDto newsEntityToDTO(NewsEntity newsEntity) {
        NewsDto newsDto = new NewsDto();
        newsDto.setId(newsEntity.getId());
        newsDto.setNewsContent(newsEntity.getContent());
        newsDto.setNewsTitle(newsEntity.getTitle());
        newsDto.setDateTime(newsEntity.getDate());
        // Добавьте маппинг photo, если есть
        return newsDto;
    }

    // Маппинг DTO -> Entity
    public static NewsEntity newsDTOToNewsEntity(NewsDto newsDto) {
        NewsEntity newsEntity = new NewsEntity();
        newsEntity.setId(newsDto.getId());
        newsEntity.setTitle(newsDto.getNewsTitle());
        newsEntity.setContent(newsDto.getNewsContent());
        newsEntity.setDate(newsDto.getDateTime());
        // newsEntity.setAuthor(convertAuthorToEntity(newsDto.getAuthor())); // Реализуйте маппинг автора
        return newsEntity;
    }

    // Маппинг DTO -> DTO Response
    public static NewsDtoResponse newDTOToNewsDtoResponse(NewsDto newsDto) {
        NewsDtoResponse newsDtoResponse = new NewsDtoResponse();
        newsDtoResponse.setId(newsDto.getId());
        newsDtoResponse.setNewsContent(newsDto.getNewsContent());
        newsDtoResponse.setNewsTitle(newsDto.getNewsTitle());
        newsDtoResponse.setUser(newsDto.getAuthor()); // Убедитесь, что Author имеет нужный формат
        return newsDtoResponse;
    }
}
