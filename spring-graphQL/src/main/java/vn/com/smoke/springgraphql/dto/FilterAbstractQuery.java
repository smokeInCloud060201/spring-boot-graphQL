package vn.com.smoke.springgraphql.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class FilterAbstractQuery {
    public static class Filter {}
    public static class Order {}

    private List<Filter> and;
    private List<Filter> or;
    private Order order;

    Integer pageIndex;
    Integer pageSize;
}
