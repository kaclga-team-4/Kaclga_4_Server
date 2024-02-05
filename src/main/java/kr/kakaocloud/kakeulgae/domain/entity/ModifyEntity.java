package kr.kakaocloud.kakeulgae.domain.entity;

import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

public class ModifyEntity extends BaseEntity{
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
