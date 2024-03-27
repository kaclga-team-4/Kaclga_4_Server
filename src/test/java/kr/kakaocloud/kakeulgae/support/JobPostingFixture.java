package kr.kakaocloud.kakeulgae.support;

import com.google.firebase.database.annotations.Nullable;
import java.time.LocalDate;
import kr.kakaocloud.kakeulgae.domain.entity.JobPosting;
import kr.kakaocloud.kakeulgae.service.dto.jobposting.JobPostingSimpleResponse;

public class JobPostingFixture {

    final static String COMPANYNAME = "companyName";
    final static String POSTNAME = "postName";
    final static String URL = "url";
    final static LocalDate DEADLINE = LocalDate.now().plusDays(7);
    final static LocalDate CREATEDAT = LocalDate.now();

    public static JobPosting createJobPosting(@Nullable String companyName,
        @Nullable String postName) {
        companyName = companyName == null ? COMPANYNAME : companyName;
        postName = postName == null ? POSTNAME : postName;
        return new JobPosting(companyName, postName, EducationFixture.createEducation(),
            CareerFixture.createCareer(), URL, DEADLINE, CREATEDAT);
    }

    public JobPostingSimpleResponse createJobPostingSimpleResponse(@Nullable JobPosting job) {
        job = job == null ? createJobPosting(COMPANYNAME, POSTNAME) : job;
        return new JobPostingSimpleResponse(job);
    }

}
