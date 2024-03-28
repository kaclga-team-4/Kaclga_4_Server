package kr.kakaocloud.kakeulgae.service.dto.notification;

import java.time.LocalDate;
import kr.kakaocloud.kakeulgae.domain.entity.Notification;
import kr.kakaocloud.kakeulgae.service.dto.jobposting.JobPostingSimpleResponse;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotificationListDto {

    private Long id;
    private long memberId;
    private JobPostingSimpleResponse jobPosting;
    private LocalDate createdAt;

    public NotificationListDto(Notification notification) {
        this.id = notification.getId();
        this.memberId = notification.getMember().getId();
        this.jobPosting = new JobPostingSimpleResponse(notification.getJobPosting());
        this.createdAt = notification.getCreatedAt();
    }
}
