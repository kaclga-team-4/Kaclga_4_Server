package kr.kakaocloud.kakeulgae.controller;


import java.util.HashMap;
import java.util.List;
import kr.kakaocloud.kakeulgae.service.TestService;
import kr.kakaocloud.kakeulgae.service.dto.TestPostRequest;
import kr.kakaocloud.kakeulgae.service.dto.TestResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    private final TestService imageService;

    @PostMapping()
    public void createTest(@RequestBody
    HashMap<String, Object> body) {

        System.out.println(body);
    }

    @GetMapping("{id}")
    public ResponseEntity<TestResponse> getName(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(testService.getId(id));
    }

    @PostMapping("/image")
    @ResponseStatus(HttpStatus.OK)
    public List<String> saveImage(@RequestBody TestPostRequest imageSaveDto) {
        return imageService.saveImages(imageSaveDto);
    }
}
