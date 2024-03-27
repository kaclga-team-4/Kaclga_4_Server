package kr.kakaocloud.kakeulgae.service;

import java.util.ArrayList;
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

    public List<Career> findAllCareer() {
        return careerRepository.findAll();
    }

    public List<Career> saveUserCareer(Member member, List<Long> careerIds) {
        List<Career> careers = careerRepository.findByIdIn(careerIds);
        ArrayList<CareerMemberRelation> careerMemberRelations = new ArrayList<>();
        for (Career career : careers) {
            careerMemberRelations.add(CareerMemberRelation.createRelation(member, career));
        }
        careerMemberRepository.saveAll(careerMemberRelations);

        return careers;
    }

    public List<Career> updateUserCareer(Member member, List<Long> careerIds) {
        List<Career> careers = careerRepository.findByIdIn(careerIds);
        List<CareerMemberRelation> careerMemberRelations = careerMemberRepository.findByMember(member);
        careerMemberRepository.deleteAll(careerMemberRelations);

        List<CareerMemberRelation> newCareerMemberRelations = new ArrayList<>();

        for (Career career : careers) {
            careerMemberRelations.add(CareerMemberRelation.createRelation(member, career));
        }
        careerMemberRepository.saveAll((careerMemberRelations));

        return careers;
    }
}
