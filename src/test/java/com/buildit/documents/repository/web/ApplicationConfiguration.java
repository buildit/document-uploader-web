package com.buildit.documents.repository.web;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class ApplicationConfiguration {

    @Autowired
    private S3Wrapper s3Wrapper;

    @Bean
    @Primary
    public S3Wrapper s3Wrapper() {
        return Mockito.mock(S3Wrapper.class);
    }

    @Bean
    @Primary
    public AmazonS3Client amazonS3Client() {
        return Mockito.mock(AmazonS3Client.class);
    }
}
