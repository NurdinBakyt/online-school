package org.nurdin.school.controller;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import org.nurdin.school.configuration.user.MinioConfig;
import org.nurdin.school.service.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/minio")
public class ImageController {
    private ImageService imageService;
    private final MinioClient minioClient;
    private final MinioConfig minioConfig;

    public ImageController(ImageService imageService, MinioClient minioClient, MinioConfig minioConfig) {
        this.imageService = imageService;
        this.minioClient = minioClient;
        this.minioConfig = minioConfig;
    }

    @GetMapping("/get-buckets")
    public ResponseEntity<List<Bucket>> getBuckets() throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        return ResponseEntity.ok(minioClient.listBuckets());
    }


    @GetMapping("/download/{filename}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String filename) {
        try {
            InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                    .bucket(minioConfig.getBucket())
                    .object(filename)
                    .build()
            );

            String contentType = Files.probeContentType(Paths.get(filename));
            if (contentType == null) {
                contentType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
            }

            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"");
            headers.add(HttpHeaders.CONTENT_TYPE, contentType);

            return ResponseEntity.ok()
                .headers(headers)
                .body(new InputStreamResource(stream));
        } catch (ErrorResponseException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (IOException | ServerException | InsufficientDataException |
                 NoSuchAlgorithmException | InvalidKeyException | InvalidResponseException |
                 XmlParserException | InternalException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
