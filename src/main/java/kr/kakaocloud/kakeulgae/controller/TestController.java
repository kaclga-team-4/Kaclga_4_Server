package kr.kakaocloud.kakeulgae.controller;

import kr.kakaocloud.kakeulgae.domain.dto.TestPostRequest;
import kr.kakaocloud.kakeulgae.domain.dto.TestResponse;
import kr.kakaocloud.kakeulgae.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final TestService testService;

    @PostMapping()
    public void createTest(@RequestBody TestPostRequest t){
        testService.createTest(t);
    }
    @GetMapping("{id}")
    public ResponseEntity<TestResponse> getName(@PathVariable(value="id") Long id) {
        return ResponseEntity.ok(testService.getId(id));
    }
}
