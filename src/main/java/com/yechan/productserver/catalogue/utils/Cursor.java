package com.yechan.productserver.catalogue.utils;

import com.yechan.productserver.common.dto.CursorDto;
import com.yechan.productserver.common.exception.ProductException;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Cursor<T> {

    public CursorDto getCursorDto(List<T> list, Integer limit, Long lastId) {
        boolean isNext = limit <= list.size();

        if (isNext) {
            list.remove(list.size() - 1);
        }

        return CursorDto.builder()
            .list(list)
            .isNext(isNext)
            .cursor(++lastId)
            .limit(limit)
            .totalCount(list.size())
            .build();
    }

    public T getLastItem(List<T> list) {
        if (list.isEmpty()) {
            throw new ProductException();
        }

        return list.get(list.size() - 1);
    }

}
