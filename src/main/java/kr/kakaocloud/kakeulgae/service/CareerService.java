package kr.kakaocloud.kakeulgae.service;

import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.Career;
import kr.kakaocloud.kakeulgae.domain.entity.member.CareerMemberRelation;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.repository.CareerMemberRepository;
import kr.kakaocloud.kakeulgae.repository.CareerRepository;
import kr.kakaocloud.kakeulgae.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CareerService {

    private final CareerRepository careerRepository;
    private final CareerMemberRepository careerMemberRepository;
    private final MemberRepository memberRepository;

    public void saveUserCareer(Member member, List<String> careers) {
        List<Career> findCareers = careerRepository.findByTypeIn(careers);

        for (Career findCareer : findCareers) {
            CareerMemberRelation relation = CareerMemberRelation.createRelation(member, findCareer);
            careerMemberRepository.save(relation);
        }
    }

    public void updateUserCareer(Member member, List<String> careers) {
        List<Career> findCareers = careerRepository.findByTypeIn(careers);
        List<CareerMemberRelation> careerMemberRelations = careerMemberRepository.findByMember(member);
        careerMemberRepository.deleteAll(careerMemberRelations);

        for (Career findCareer : findCareers) {
            CareerMemberRelation relation = CareerMemberRelation.createRelation(member, findCareer);
            careerMemberRepository.save(relation);
        }
    }
}
