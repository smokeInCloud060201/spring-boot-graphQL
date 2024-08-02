package vn.com.smoke.springgraphql.dto.person;

import lombok.Getter;
import lombok.Setter;
import vn.com.smoke.springgraphql.dto.FilterAbstractQuery;
import vn.com.smoke.springgraphql.dto.graphqlquery.OrderType;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.impl.BooleanExpression;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.impl.IntegerExpression;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.impl.LocalDateExpression;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.impl.StringExpression;

@Getter
@Setter
public class PersonQuery extends FilterAbstractQuery {

    @Getter
    @Setter
    public static class Filter extends FilterAbstractQuery.Filter {
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
    public static class Order extends FilterAbstractQuery.Order {
        private OrderType id;
        private OrderType name;
        private OrderType dateOfBirth;
        private OrderType emailVerified;
        private OrderType creditScore;
    }
}
