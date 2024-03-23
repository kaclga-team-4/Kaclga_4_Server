package kr.kakaocloud.kakeulgae.service.dto.member;

import kr.kakaocloud.kakeulgae.support.validator.NullOrNotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberUpdateReqeust {

    @NullOrNotBlank
    private String nickname;
    @NullOrNotBlank
    private String email;
    @NullOrNotBlank
    private String phoneNumber;
}
