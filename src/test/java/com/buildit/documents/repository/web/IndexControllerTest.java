package com.buildit.documents.repository.web;

import com.amazonaws.services.s3.model.PutObjectResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, properties = "cloud.aws.s3.bucket=bucket")
public class IndexControllerTest {

    @Value("${local.server.port}")
    private int localServerPort;

    @Value("${api.path}")
    private String path;

    @Value("${server.servlet.context-path}")
    private String basePath;

    @Autowired
    private S3Wrapper s3Wrapper;

    @Test
    public void testFetchAll() throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        ClassPathResource resource = new ClassPathResource("test.txt");

        MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>();
        map.add("file", resource);
        String url = "http://localhost:" + localServerPort + basePath + path + "/upload";
        ResponseEntity<String> response = restTemplate.postForEntity(url, map, String.class);

        List<PutObjectResult> result = new ArrayList<>();
        when(s3Wrapper.upload(any(MultipartFile[].class))).thenReturn(result);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

}