package kr.kakaocloud.kakeulgae.domain.entity.member;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.kakaocloud.kakeulgae.domain.entity.Career;
import kr.kakaocloud.kakeulgae.repository.CareerMemberRepository;
import lombok.Getter;

@Getter
@Entity
@Table(
    name = "career_member_relation"
)
public class CareerMemberRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",
        foreignKey = @ForeignKey(name = "fk_career_member_relation_member_id"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "career_id",
        foreignKey = @ForeignKey(name = "fk_career_member_relation_career_id"))
    private Career career;

    public CareerMemberRelation(Member member, Career career) {
        this.member = member;
        this.career = career;
    }

    public static CareerMemberRelation createRelation(Member member, Career career) {
        return new CareerMemberRelation(member, career);
    }
}
