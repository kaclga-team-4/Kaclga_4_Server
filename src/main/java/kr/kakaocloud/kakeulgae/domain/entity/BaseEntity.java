package kr.kakaocloud.kakeulgae.domain.entity;

import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

public class BaseEntity {
    @CreatedDate
    private LocalDateTime createdAt;

}
