package org.nurdin.school.util.mappers;

import org.nurdin.school.dto.news.NewsImage;
import org.nurdin.school.dto.news.NewsImageDTO;
import org.nurdin.school.util.Mappable;
import org.springframework.stereotype.Component;

@Component
public interface NewsImageMapper extends Mappable<NewsImage, NewsImageDTO> {
}
