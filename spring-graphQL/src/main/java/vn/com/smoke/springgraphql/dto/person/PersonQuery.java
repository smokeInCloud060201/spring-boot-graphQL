package vn.com.smoke.springgraphql.dto.person;

import vn.com.smoke.springgraphql.dto.FilterAbstractQuery;
import vn.com.smoke.springgraphql.dto.graphqlquery.OrderType;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.impl.BooleanExpression;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.impl.IntegerExpression;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.impl.LocalDateExpression;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.impl.StringExpression;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonQuery extends FilterAbstractQuery {

    @Getter
    @Setter
    public static class Filter {
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
    public static class Order {
        private OrderType id;
        private OrderType name;
        private OrderType dateOfBirth;
        private OrderType emailVerified;
        private OrderType creditScore;
    }

    private List<Filter> and;
    private List<Filter> or;
    private Order order;

    Integer pageIndex;
    Integer pageSize;
}
