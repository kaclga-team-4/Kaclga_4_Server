package kr.kakaocloud.kakeulgae.repository;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.JobDetail;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.domain.entity.member.PreferenceJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreferenceJobRepository extends JpaRepository<PreferenceJob, Long> {

    List<PreferenceJob> findByMember(Member member);
}
