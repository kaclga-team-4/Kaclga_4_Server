package kr.kakaocloud.kakeulgae.domain.entity.member;

import jakarta.annotation.Nullable;
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
import kr.kakaocloud.kakeulgae.support.domain.BaseModifiableEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "member",
    indexes = @Index(name = "profile_id_index", columnList = "profile_id"))
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    @Column(nullable = false, updatable = false, columnDefinition = "enum")
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "enum")
    private MemberRole memberRole;

    @Column(nullable = false, columnDefinition = "enum")
    @Builder.Default
    @Enumerated(EnumType.STRING)
    private NoticeCheck noticeCheck = NoticeCheck.CHECKED;

    @Column(nullable = false, length = 24, unique = true)
    private String nickname;

    @Column(length = 13, unique = true)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum")
    private Gender gender;

    @Column(updatable = false)
    private LocalDate birthday;

    @JoinColumn(name = "profile_id", nullable = false)
    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE,
        CascadeType.REMOVE}) // member가 삭제되면 profile도 삭제
    private Profile profile;
}
