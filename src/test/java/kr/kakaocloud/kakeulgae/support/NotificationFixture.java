package kr.kakaocloud.kakeulgae.support;

import com.google.firebase.database.annotations.Nullable;
import java.time.LocalDateTime;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import kr.kakaocloud.kakeulgae.domain.entity.Notification;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.service.dto.notification.NotificationListDto;

public class NotificationFixture {

    public final static int DEFAULT_SIZE = 10;
    public final static Long DEFAULT_LAST_ID = 1L;

    public static Notification createNotification(Long id, @Nullable Member member,
        @Nullable JobPosting jobPosting) {
        member = member == null ? MemberFixture.createMember() : member;
        jobPosting =
            jobPosting == null ? JobPostingFixture.createJobPosting(null, null) : jobPosting;
        Notification notification = new Notification(id, member, jobPosting);
        notification.setCreatedAt(LocalDateTime.now());
        return notification;
    }

    public static NotificationListDto createNotificationListDto(Member member,
        JobPosting jobPosting) {
        member = member == null ? MemberFixture.createMember() : member;
        jobPosting =
            jobPosting == null ? JobPostingFixture.createJobPosting(null, null) : jobPosting;
        return new NotificationListDto(createNotification(1L, member, jobPosting));
    }


}
