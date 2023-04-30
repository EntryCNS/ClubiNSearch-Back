package com.dgsw.cns.clubinsearch.thirdparty.s3.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import com.amazonaws.util.IOUtils;
import com.dgsw.cns.clubinsearch.thirdparty.s3.enums.Dir;
import com.dgsw.cns.clubinsearch.thirdparty.s3.exception.FileUploadFailedException;
import com.dgsw.cns.clubinsearch.thirdparty.s3.properties.S3Properties;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3Client amazonS3Client;
    private final S3Properties s3Properties;

    public String uploadFile(
        Dir dir,
        MultipartFile multipartFile
    ) {
        if (multipartFile == null) {
            return "";
        }
        String fileName = buildFile(dir, multipartFile.getOriginalFilename());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());

        try {

            PutObjectRequest request = new PutObjectRequest(
                    s3Properties.getBucket(),
                    fileName,
                    multipartFile.getInputStream(),
                    objectMetadata
            );
            amazonS3Client.putObject(request.withCannedAcl(CannedAccessControlList.Private));
        } catch (IOException e) {
            throw FileUploadFailedException.Exception;
        }
        return amazonS3Client.getUrl(s3Properties.getBucket(), fileName).toString();
    }

    private String buildFile(Dir dir, String originalName) {
        return dir.toString().toLowerCase() + "/" + UUID.randomUUID() + "-" + originalName;
    }
}
