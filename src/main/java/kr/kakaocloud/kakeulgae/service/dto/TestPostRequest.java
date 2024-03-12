package kr.kakaocloud.kakeulgae.service.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
//getter, setter, hashcode, toString, equals 자동 재정의
@NoArgsConstructor
@AllArgsConstructor
public class TestPostRequest {

    private String name;

    private List<MultipartFile> images = new ArrayList<>();

    public TestPostRequest(String testName) {
        this.name = testName;
    }
}
