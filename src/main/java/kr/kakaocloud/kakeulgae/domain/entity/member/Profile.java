package kr.kakaocloud.kakeulgae.domain.entity.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.kakaocloud.kakeulgae.support.domain.BaseModifiableEntity;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor//기본 생성자를 생성
public class Profile extends BaseModifiableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String url;

    public Profile(String url) {
        this.url = url;
    }

    public Profile(Long id, String url) {
        this.id = id;
        this.url = url;
    }

    public void changeProfile(
        String url) {
        this.url = url;
    }
}
