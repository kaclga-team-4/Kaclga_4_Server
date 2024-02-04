package kr.kakaocloud.kakeulgae.controller;
import kr.kakaocloud.kakeulgae.domain.dto.TestImageSaveDto;
import kr.kakaocloud.kakeulgae.service.TestImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestImageController {

    private final TestImageService imageService;

    @PostMapping("/image")
    @ResponseStatus(HttpStatus.OK)
    public List<String> saveImage(@ModelAttribute TestImageSaveDto imageSaveDto) {
        return imageService.saveImages(imageSaveDto);
    }
}