package kr.kakaocloud.kakeulgae.service;

import static kr.kakaocloud.kakeulgae.service.TestService.bucketName;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Service
public class FileService {

    private final AmazonS3Client amazonS3Client;

    public String saveProfile(MultipartFile file) {
        String filename = STR."profile/\{createUuid()}";
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata(); //파일 메타데이터 생성
            objectMetadata.setContentType(file.getContentType()); //파일 타입 설정
            objectMetadata.setContentLength(file.getInputStream().available()); //파일 크기 설정
            amazonS3Client.putObject(bucketName, filename, file.getInputStream(),
                objectMetadata); //파일 저장을 위해 버킷이름과 파일 이름, 파일 스트림, 파일 메타데이터를 전달
            return amazonS3Client.getUrl(bucketName, filename).toString();
        } catch (IOException e) {
            throw new RuntimeException("파일 저장에 실패했습니다.");
        }
    }

    private String createUuid() {
        return UUID.randomUUID().toString();
    } //UUID 생성(랜덤 문자열 생성)
}
