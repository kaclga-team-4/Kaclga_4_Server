package kr.kakaocloud.kakeulgae.service.dto.member;

import javax.annotation.Nullable;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.domain.entity.member.NoticeCheck;
import lombok.Getter;

@Getter
public class MemberResponse { //회원 정보 응답 클래스(회원 정보 조회시 사용) - 테스트

    private Long id;
    private String email;
    private String nickname;
    @Nullable
    private String phoneNumber;
    private NoticeCheck noticeCheck;


    public MemberResponse(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.nickname = member.getNickname();
        this.phoneNumber = member.getPhoneNumber();
        this.noticeCheck = member.getNoticeCheck();
    }
}
