package kr.kakaocloud.kakeulgae.domain.entity.member;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import javax.annotation.Nullable;
import kr.kakaocloud.kakeulgae.support.domain.BaseModifiableEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(
    name = "member",
    indexes = @Index(name = "profile_id_index", columnList = "profile_id"))
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Member extends BaseModifiableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false, unique = true)
    private String memberName;

    @Column(unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberRole memberRole;

    @Column(nullable = false)
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private NoticeCheck noticeCheck = NoticeCheck.CHECKED;

    @Column(nullable = false, length = 24, unique = true)
    private String nickname;

    @Column(length = 13, unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Gender gender;

    @Column(updatable = false)
    private LocalDate birthday;

    @JoinColumn(name = "profile_id", nullable = false)
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.REMOVE}) // member가 삭제되면 profile도 삭제
    private Profile profile;

    public Member(Long id, String MemberName,
        String email,
        @Nullable String nickname,
        @Nullable String phoneNumber,
        @Nullable Gender gender,
        @Nullable LocalDate birthday,
        SocialType socialType,
        @Nullable MemberRole memberRole,
        Profile profile,
        NoticeCheck noticeCheck
    ) {
        this.id = id;
        this.memberName = MemberName;
        this.socialType = socialType;
        this.memberRole = memberRole;
        this.nickname = nickname;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
        this.birthday = birthday;
        this.profile = profile;
        this.email = email;
        this.noticeCheck = noticeCheck;
    }

    public void changeProfile(String profileName, String originFileName) {
        profile.changeProfile(profileName, originFileName);
    }
}
