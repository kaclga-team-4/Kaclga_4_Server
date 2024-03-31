package kr.kakaocloud.kakeulgae.repository.PreferenceJob;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.domain.entity.member.PreferenceJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceJobRepository extends JpaRepository<PreferenceJob, Long>,
    PreferenceJobRepositoryCustom {

    List<PreferenceJob> findByMember(Member member);

    List<Member> findMemberByJobPosting(JobPosting jobPosting);
}
