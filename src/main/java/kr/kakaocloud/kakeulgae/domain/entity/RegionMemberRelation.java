package kr.kakaocloud.kakeulgae.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kr.kakaocloud.kakeulgae.domain.entity.member.Member;
import lombok.Getter;

@Getter
@Entity
@Table(
    name = "region_member_relatoin"
)
public class RegionMemberRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",
        foreignKey = @ForeignKey(name = "fk_region_member_relation_member_id"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_2nd_id",
        foreignKey = @ForeignKey(name = "fk_region_member_relation_region_2nd_id"))
    private Region2nd region2nd;
}
