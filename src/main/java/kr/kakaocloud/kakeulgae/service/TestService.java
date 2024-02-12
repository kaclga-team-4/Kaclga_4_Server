package kr.kakaocloud.kakeulgae.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import kr.kakaocloud.kakeulgae.domain.dto.TestPostRequest;
import kr.kakaocloud.kakeulgae.domain.dto.TestResponse;
import kr.kakaocloud.kakeulgae.domain.entity.*;
import kr.kakaocloud.kakeulgae.domain.repository.DataRepository;
import kr.kakaocloud.kakeulgae.domain.repository.DataRepository2;
import kr.kakaocloud.kakeulgae.domain.repository.TestImageRepository;
import kr.kakaocloud.kakeulgae.domain.repository.TestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    private static String bucketName = "kaclgae-s3";

    private final AmazonS3Client amazonS3Client;

    private final TestImageRepository imageRepository;

    private final DataRepository dataRepository;

    private final DataRepository2 dataRepository2;

    public void createTest(TestPostRequest t){
        TestEntity test = TestEntity.builder().name(t.getName()).build();
        testRepository.save(test);
    }
    public TestResponse getId(Long id) {
        TestEntity test = testRepository.findById(id).orElseThrow();
        return TestResponse.of(test);
    }

    @Transactional
    public List<String> saveImages(TestPostRequest saveDto) {
        List<String> resultList = new ArrayList<>();

        for(MultipartFile multipartFile : saveDto.getImages()) {
            String value = saveImage(multipartFile);
            resultList.add(value);
        }

        return resultList;
    }

    @Transactional
    public String saveImage(MultipartFile multipartFile) {
        String originalName = multipartFile.getOriginalFilename();
        TestImage image = new TestImage(originalName);
        String filename = image.getStoredName();

        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(multipartFile.getContentType());
            objectMetadata.setContentLength(multipartFile.getInputStream().available());

            amazonS3Client.putObject(bucketName, filename, multipartFile.getInputStream(), objectMetadata);

            String accessUrl = amazonS3Client.getUrl(bucketName, filename).toString();
            image.setAccessUrl(accessUrl);
        } catch(IOException e) {

        }

        imageRepository.save(image);

        return image.getAccessUrl();
    }

    @Transactional
    public void importData(String content) {
        List<JobDetail> list = new ArrayList<>();
        String[] splts = content.split("\r\n");
        for (String s : splts) {
            String[] splts2 = s.split("\t+");
            Job region1st = dataRepository.findById(Long.parseLong(splts2[0])).orElseThrow();
            list.add(new JobDetail(Long.parseLong(splts2[2]), splts2[3],region1st));
        }
        dataRepository2.saveAll(list);
    }
    /*
    @Transactional
    public void importData(String content) {
        List<Job> list = new ArrayList<>();
        String[] splts = content.split("\r\n");
        for (String s : splts) {
            String[] splts2 = s.split("\t+");
            list.add(new Job(Long.parseLong(splts2[0]), splts2[1]));
        }
        dataRepository.saveAll(list);
    }
    @Transactional
    public void importData(String content) {
            List<Region2st> list = new ArrayList<>();
            String[] splts = content.split("\r\n");
            for (String s : splts) {
                String[] splts2 = s.split("\t+");
                list.add(new Region2st(Long.parseLong(splts2[0]), splts2[1]));
            }
            dataRepository.saveAll(list);
        }
    @Transactional
    public void importData(String content) {
        List<Region2st> list = new ArrayList<>();
        String[] splts = content.split("\r\n");
        for (String s : splts) {
            String[] splts2 = s.split("\t+");
            Region1st region1st = dataRepository.findById(Long.parseLong(splts2[3])).orElseThrow();
            list.add(new Region2st(Long.parseLong(splts2[0]), splts2[1],region1st));
        }
        dataRepository2.saveAll(list);
    }*/
}
