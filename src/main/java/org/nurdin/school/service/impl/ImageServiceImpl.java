package org.nurdin.school.service.impl;

import io.minio.*;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.nurdin.school.dto.news.NewsImage;
import org.nurdin.school.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    private static final Logger log = LoggerFactory.getLogger(ImageServiceImpl.class);
    private final MinioClient minioClient;
    private final String bucketName;

    public ImageServiceImpl(MinioClient minioClient, @Value("${minio.bucket}") String bucketName) {
        this.minioClient = minioClient;
        this.bucketName = bucketName;
    }

    @Override
    public String upload(NewsImage newsImage) {
        try {
            this.createBucket();
        } catch (RuntimeException e) {
            throw new RuntimeException("Ошибка при создании бакета: " + e.getMessage(), e);
        }

        MultipartFile multipartFile = newsImage.getMultipartFile();
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new RuntimeException("Файл не должен быть пустым!");
        }

        String fileName = generateFileName(multipartFile);
        try (InputStream inputStream = multipartFile.getInputStream()) {
            saveImage(inputStream, fileName);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при сохранении файла: " + e.getMessage(), e);
        }

        return fileName;
    }

    @Override
    public void download(String key) {
        try {
            minioClient.downloadObject(
                DownloadObjectArgs.builder()
                    .object(key)
                    .bucket(bucketName)
                    .build()
            );

        } catch (ServerException | InsufficientDataException | ErrorResponseException |
                 IOException | NoSuchAlgorithmException | InvalidKeyException |
                 InvalidResponseException | XmlParserException | InternalException e) {
            throw new RuntimeException(e);
        }
    }




    private String generateFileName(MultipartFile multipartFile) {
        String extension = getExtension(multipartFile);
        return UUID.randomUUID() + "." + extension;
    }

    private String getExtension(MultipartFile multipartFile) {
        String originalFilename = multipartFile.getOriginalFilename();
        if (originalFilename == null || originalFilename.lastIndexOf(".") == -1) {
            throw new RuntimeException("Файл должен иметь расширение!");
        }
        return originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
    }

    private void createBucket() {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(bucketName)
                .build());
            System.out.println(found);
            if (!found) {
                System.out.println("Бакета нету создаем новый бакет");
                minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());

            } else {
                System.out.println("Бакет уже есть");
            }
        } catch (ServerException | InsufficientDataException | ErrorResponseException |
                 IOException | NoSuchAlgorithmException | InvalidKeyException |
                 InvalidResponseException | XmlParserException | InternalException e) {
            throw new RuntimeException(e + "Ошибки при создании bucket create");
        }
    }

    private void saveImage(InputStream inputStream, String fileName) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.putObject(PutObjectArgs.builder()
            .stream(inputStream, inputStream.available(), -1)
            .bucket(bucketName)
            .object(fileName)
            .build());
    }
}
