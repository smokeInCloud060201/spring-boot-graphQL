package vn.com.smoke.springgraphql.dto.person;

import lombok.Getter;
import lombok.Setter;
import vn.com.smoke.springgraphql.dto.FilterAbstractQuery;
import vn.com.smoke.springgraphql.dto.IQuery;
import vn.com.smoke.springgraphql.dto.graphqlquery.OrderType;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.impl.BooleanExpression;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.impl.IntegerExpression;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.impl.LocalDateExpression;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.impl.StringExpression;

import java.util.List;

@Getter
@Setter
public class PersonQuery extends FilterAbstractQuery<PersonQuery.Filter, PersonQuery.Order> {

    @Getter
    @Setter
    public static class Filter extends IQuery.Filter {
        private StringExpression id;
        private StringExpression name;
        private LocalDateExpression dateOfBirth;
        private BooleanExpression emailVerified;
        private IntegerExpression creditScore;
        private Filter and;
        private Filter or;
    }

    @Getter
    @Setter
    public static class Order extends IQuery.Order {
        private OrderType id;
        private OrderType name;
        private OrderType dateOfBirth;
        private OrderType emailVerified;
        private OrderType creditScore;
    }


    public List<Filter> and;
    public List<Filter> or;
    public Order order;
    public Integer pageIndex;
    public Integer pageSize;
}
