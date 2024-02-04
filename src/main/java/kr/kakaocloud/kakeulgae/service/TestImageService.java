package kr.kakaocloud.kakeulgae.service;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import kr.kakaocloud.kakeulgae.domain.dto.TestImageSaveDto;
import kr.kakaocloud.kakeulgae.domain.entity.TestImage;
import kr.kakaocloud.kakeulgae.domain.repository.TestImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TestImageService {

    private static String bucketName = "kaclgae-s3";

    private final AmazonS3Client amazonS3Client;
    private final TestImageRepository imageRepository;

    @Transactional
    public List<String> saveImages(TestImageSaveDto saveDto) {
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
}
