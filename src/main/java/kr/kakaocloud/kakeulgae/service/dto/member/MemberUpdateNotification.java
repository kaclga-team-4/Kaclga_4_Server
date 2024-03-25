package kr.kakaocloud.kakeulgae.service.dto.member;

import com.google.firebase.database.annotations.NotNull;
import kr.kakaocloud.kakeulgae.domain.entity.member.NoticeCheck;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberUpdateNotification {

    @NotNull
    private NoticeCheck noticeCheck;
}
