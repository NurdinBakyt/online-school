package org.nurdin.school.service.impl;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.SneakyThrows;
import org.nurdin.school.dto.news.NewsImage;
import org.nurdin.school.service.ImageService;
import org.nurdin.school.service.props.MinioProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {
    private final MinioClient minioClient;
    private final MinioProperties minioProperties;

    public ImageServiceImpl(MinioClient minioClient, MinioProperties minioProperties) {
        this.minioClient = minioClient;
        this.minioProperties = minioProperties;
    }

    @Override
    public String upload(NewsImage newsImage) {
        try {
            this.createBucket();
        } catch (RuntimeException | ServerException | InsufficientDataException |
                 ErrorResponseException | IOException | NoSuchAlgorithmException |
                 InvalidKeyException | InvalidResponseException | XmlParserException |
                 InternalException e) {
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

    private void createBucket() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        boolean found = minioClient.bucketExists(BucketExistsArgs.builder()
            .bucket(minioProperties.getBucket())
            .build());
        if (!found) {
            minioClient.makeBucket(MakeBucketArgs.builder()
                .bucket(minioProperties.getBucket())
                .build());
        }
    }


    private void saveImage(InputStream inputStream, String fileName) throws IOException, ServerException, InsufficientDataException, ErrorResponseException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        minioClient.putObject(PutObjectArgs.builder()
            .stream(inputStream, inputStream.available(), -1)
            .bucket(minioProperties.getBucket())
            .object(fileName)
            .build());
    }
}
