package kr.kakaocloud.kakeulgae.domain.entity.member;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import kr.kakaocloud.kakeulgae.support.domain.BaseModifiableEntity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor//기본 생성자를 생성
public class Profile extends BaseModifiableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 30)
    private String profileName;
    @Column(nullable = false)
    private String originFileName;

    public Profile(String profileName, String originFileName) {
        this.profileName = profileName;
        this.originFileName = originFileName;
    }

    public Profile(String profileName) {
        this.profileName = profileName;
        this.originFileName = profileName;
    }
    public void changeProfile(
        String profileName,
        String originFileName) {
        this.profileName = profileName;
        this.originFileName = originFileName;
    }
}
