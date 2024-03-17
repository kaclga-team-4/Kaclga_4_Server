package kr.kakaocloud.kakeulgae.service.dto;

import java.util.List;
import lombok.Getter;
import org.springframework.data.domain.Slice;

@Getter
public class SliceResponse {
    private final List<JobPostingDto> content;
    private final SortResponse sort;
    private final int currentPage;
    private final int size;
    private final boolean first;
    private final boolean last;

    public SliceResponse(Slice<JobPostingDto> sliceContent){
        this.content=sliceContent.getContent();
        this.sort=new SortResponse(sliceContent.getSort());
        this.currentPage=sliceContent.getNumber();
        this.size=sliceContent.getSize();
        this.first=sliceContent.isFirst();
        this.last=sliceContent.isLast();
    }
}
