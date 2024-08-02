package vn.com.smoke.springgraphql.dto.graphqlquery;

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

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonQuery {

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Filter {
        private StringExpression id;
        private StringExpression name;
        private LocalDateExpression dateOfBirth;
        private BooleanExpression emailVerified;
        private IntegerExpression creditScore;
        private Filter and;
        private Filter or;
    }

    @Builder
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Order {
        private OrderType id;
        private OrderType name;
        private OrderType dateOfBirth;
        private OrderType emailVerified;
        private OrderType creditScore;
    }

    private List<Filter> and;
    private List<Filter> or;
    private List<Order> orders;

    Integer pageIndex;
    Integer pageSize;
}
