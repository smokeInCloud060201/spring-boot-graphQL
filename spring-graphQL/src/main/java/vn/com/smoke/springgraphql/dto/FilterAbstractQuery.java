package vn.com.smoke.springgraphql.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FilterAbstractQuery<F extends IQuery.Filter, O extends IQuery.Order> {

    protected List<F> and;
    protected List<F> or;
    protected O order;

    protected Integer pageIndex;
    protected Integer pageSize;
}
