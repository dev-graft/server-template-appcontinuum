package devgraft.support.advice;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;

@Builder
@Getter
public class PageableResult<T> {
    private final int totalPage;    // 페이지
    private final long totalElements; // 최대 크기
    private final List<T> values;

    public static PageableResult<Object> from(final Page<Object> pageObject) {
        final List<Object> content = pageObject.getContent();
        return PageableResult.<Object>builder()
                .totalPage(pageObject.getTotalPages())
                .totalElements(pageObject.getTotalElements())
                .values(content)
                .build();
    }
}
