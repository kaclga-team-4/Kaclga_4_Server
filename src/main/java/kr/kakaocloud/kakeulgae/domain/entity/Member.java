package kr.kakaocloud.kakeulgae.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "nickname")
    private String nickName;
    private String email;
    @Column(name = "call_number")
    private String callNumber;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private LocalDate birth;

    @Enumerated(EnumType.STRING)
    private NoticeCheck noticeCheck;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", foreignKey = @ForeignKey(name = "fk_member_profile_id"))
    private Profile profile;
}
