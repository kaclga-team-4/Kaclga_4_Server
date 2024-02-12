package kr.kakaocloud.kakeulgae.controller;

import kr.kakaocloud.kakeulgae.domain.dto.TestPostRequest;
import kr.kakaocloud.kakeulgae.domain.dto.TestResponse;
import kr.kakaocloud.kakeulgae.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final TestService testService;

    private final TestService imageService;

    @PostMapping()
    public void createTest(@RequestBody TestPostRequest t){
        testService.createTest(t);
    }
    @GetMapping("{id}")
    public ResponseEntity<TestResponse> getName(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok(testService.getId(id));
    }

    @PostMapping("/image")
    @ResponseStatus(HttpStatus.OK)
    public List<String> saveImage(@ModelAttribute TestPostRequest imageSaveDto) {
        return imageService.saveImages(imageSaveDto);
    }

    @PostMapping("/db-import")
    @ResponseStatus(HttpStatus.OK)
    public void importData(@RequestBody String content) {
        testService.importData(content);
    }
}
