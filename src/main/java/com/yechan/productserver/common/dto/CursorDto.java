package com.yechan.productserver.common.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class CursorDto {
    private List list;
    private Boolean isNext;
    private Long cursor;
    private Integer totalCount;
    private Integer limit;

    public static CursorDto empty() {
        return CursorDto.builder()
            .isNext(false)
            .totalCount(0)
            .build();
    }
}
