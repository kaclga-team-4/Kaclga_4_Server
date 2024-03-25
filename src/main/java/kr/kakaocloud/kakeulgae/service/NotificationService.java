package kr.kakaocloud.kakeulgae.service;

import kr.kakaocloud.kakeulgae.service.dto.jobposting.JobPostingListDto;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public Slice<JobPostingListDto> getNotificationList() {
        return null;
    }
}
