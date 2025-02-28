package org.nurdin.school.service;

import org.nurdin.school.dto.news.NewsImage;

import java.io.IOException;


public interface ImageService {
    String upload(NewsImage newsImage) ;
    void download(String filename) throws IOException;
}
