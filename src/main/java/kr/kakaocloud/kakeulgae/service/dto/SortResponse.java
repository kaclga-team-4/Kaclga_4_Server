package kr.kakaocloud.kakeulgae.service.dto;

import lombok.Getter;
import org.springframework.data.domain.Sort;

@Getter
public class SortResponse {
    private final boolean sorted; // 정렬 여부
    private final Sort.Direction direction; // 정렬 방향
    private final String orderProperty; // 정렬 기준

    public SortResponse(Sort sort) {
        this.sorted = sort.isSorted();
        if (sort.isSorted()) {
            this.direction = sort.getOrderFor(sort.stream().findFirst().get().getProperty()).getDirection();
            this.orderProperty = sort.stream().findFirst().get().getProperty();
        } else {
            this.direction = null;
            this.orderProperty = null;
        }
    }
}