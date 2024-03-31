package kr.kakaocloud.kakeulgae.repository.PreferenceJob;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;

public interface PreferenceJobRepositoryCustom {

    List<Member> findMemberByJobPosting(JobPosting jobPosting);
}
