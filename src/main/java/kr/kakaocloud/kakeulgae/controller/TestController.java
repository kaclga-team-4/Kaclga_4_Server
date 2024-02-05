package kr.kakaocloud.kakeulgae.controller;

import jakarta.validation.Valid;
import kr.kakaocloud.kakeulgae.domain.dto.TestDtos.TestPostRequest;
import kr.kakaocloud.kakeulgae.domain.dto.TestDtos.TestResponse;
import kr.kakaocloud.kakeulgae.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {
    private final TestService testService;

    @PostMapping()
    public ResponseEntity<Void> createTest(@Valid @RequestBody TestPostRequest t){
        testService.createTest(t);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("{id}")
    public ResponseEntity<TestResponse> getName(@PathVariable Long id) {
        return new ResponseEntity<>(testService.getId(id), HttpStatus.OK);
    }
}
