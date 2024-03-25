package kr.kakaocloud.kakeulgae.support.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@MappedSuperclass //부모 클래스에 선언하고 속성만 상속 받아서 사용하고 싶을 때 @MappedSuperclass를 사용(엔티티로 선언 X)
@EntityListeners({AuditingEntityListener.class})//엔팉가 삽입, 삭제 등의 작업 전 후에 이벤트 처리를 위한 어노테이션
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    LocalDateTime createdAt;

    protected LocalDate getCreatedAt() {
        return createdAt.toLocalDate();
    }
}
