package kr.kakaocloud.kakeulgae.service.dto.SliceResponse;

import java.util.List;
import org.springframework.data.domain.Pageable;

public class SliceResponse<T> {

    Boolean hasNext;
    List<T> content;

    public SliceResponse(int size, List<T> content) {
        this.hasNext = content.size() > size;
        this.content = content.size() > size ? content.subList(0, size) : content;
    }

    public SliceResponse(Pageable pageable, List<T> content) {
        this(pageable.getPageSize(), content);
    }
}
