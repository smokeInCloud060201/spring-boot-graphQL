package vn.com.smoke.springgraphql.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Paginated<T> {

    private List<T> content;
    private Integer pageIndex;
    private Integer pageSize;
    private Long totalCount;
    private Integer pageCount;
}
