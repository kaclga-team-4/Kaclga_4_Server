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
import kr.kakaocloud.kakeulgae.domain.entity.WorkType;
import lombok.Getter;

@Getter
@Entity
@Table(
    name = "work_type_member_relation"
)
public class WorkTypeMemberRelation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",
        foreignKey = @ForeignKey(name = "fk_work_type_member_relation_member_id"))
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_type_id",
        foreignKey = @ForeignKey(name = "fk_work_type_member_relation_work_type_id"))
    private WorkType workType;
}
