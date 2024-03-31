package kr.kakaocloud.kakeulgae.service;

import java.util.ArrayList;
import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import kr.kakaocloud.kakeulgae.domain.entity.LastJobPostingId;
import kr.kakaocloud.kakeulgae.domain.entity.Notification;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.repository.JobPostingRepository;
import kr.kakaocloud.kakeulgae.repository.LastJobPostingId.LastJobPostingIdRepository;
import kr.kakaocloud.kakeulgae.repository.Notification.NotificationRepository;
import kr.kakaocloud.kakeulgae.repository.PreferenceJob.PreferenceJobRepository;
import kr.kakaocloud.kakeulgae.service.dto.SliceResponse.SliceResponse;
import kr.kakaocloud.kakeulgae.service.dto.notification.NotificationListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final JobPostingRepository jobPostingRepository;
    private final LastJobPostingIdRepository lastJobPostingIdRepository;
    private final PreferenceJobRepository preferenceJobRepository;

    public SliceResponse<NotificationListDto> getNotificationList(Long memberId, int size,
        Long lastId) {
        List<NotificationListDto> notifications = notificationRepository.findListByMemberId(
            memberId, size, lastId).stream().map(NotificationListDto::new).toList();
        return new SliceResponse<>(size, notifications);
    }

    @Transactional
    @Scheduled(cron = "0 */2 * * * *")
    public void createNotification() {
        Long lastJobPostingId = lastJobPostingIdRepository.findLastJobPostingIdBy() == null ? 0
            : lastJobPostingIdRepository.findLastJobPostingIdBy();
        List<JobPosting> jobPostings = jobPostingRepository.findByIdGreaterThan(lastJobPostingId);
        if (!jobPostings.isEmpty()) {
            List<Notification> notifications = new ArrayList<>();
            for (JobPosting jobPosting : jobPostings) {
                List<Member> members = preferenceJobRepository.findMemberByJobPosting(jobPosting);
                for (Member member : members) {
                    notifications.add(new Notification(
                        STR."[\{jobPosting.getCompanyName()}] \{jobPosting.getPostName()}", member,
                        jobPosting));
                }
            }
            notificationRepository.saveAll(notifications);
            lastJobPostingIdRepository.save(new LastJobPostingId(jobPostings.getLast().getId()));
        }
    }
}
