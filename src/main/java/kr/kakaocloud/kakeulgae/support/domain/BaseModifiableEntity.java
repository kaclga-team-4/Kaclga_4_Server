package kr.kakaocloud.kakeulgae.support.domain;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import org.springframework.data.annotation.LastModifiedDate;


// 해당 엔티티는 수정가능한 엔티티에 적용한다
@MappedSuperclass
public abstract class BaseModifiableEntity extends BaseTimeEntity {

    @LastModifiedDate
    @Column(nullable = false)
    LocalDateTime updatedAt;
}
