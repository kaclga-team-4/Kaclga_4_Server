package kr.kakaocloud.kakeulgae.service;

import java.util.ArrayList;
import java.util.List;
import kr.kakaocloud.kakeulgae.domain.entity.Career;
import kr.kakaocloud.kakeulgae.domain.entity.member.CareerMemberRelation;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import kr.kakaocloud.kakeulgae.repository.CareerMemberRepository;
import kr.kakaocloud.kakeulgae.repository.CareerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CareerService {

    private final CareerRepository careerRepository;
    private final CareerMemberRepository careerMemberRepository;

    public List<Career> saveUserCareer(Member member, List<Long> careerIds) {
        List<Career> careers = careerRepository.findByIdIn(careerIds);
        ArrayList<CareerMemberRelation> careerMemberRelations = new ArrayList<>();
        for (Career career : careers) {
            careerMemberRelations.add(CareerMemberRelation.createRelation(member, career));
        }
        careerMemberRepository.saveAll(careerMemberRelations);

        return careers;
    }
}
