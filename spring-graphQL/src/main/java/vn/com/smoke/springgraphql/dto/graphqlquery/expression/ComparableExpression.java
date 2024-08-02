package vn.com.smoke.springgraphql.dto.graphqlquery.expression;

import vn.com.smoke.springgraphql.dto.graphqlquery.operator.GreaterThan;
import vn.com.smoke.springgraphql.dto.graphqlquery.operator.GreaterThanOrEqualTo;
import vn.com.smoke.springgraphql.dto.graphqlquery.operator.LessThan;
import vn.com.smoke.springgraphql.dto.graphqlquery.operator.LessThanOrEqualTo;

public interface ComparableExpression<T> {

    GreaterThan<T> getGreaterThan();

    GreaterThanOrEqualTo<T> getGreaterThanOrEqualTo();

    LessThan<T> getLessThan();

    LessThanOrEqualTo<T> getLessThanOrEqualTo();
}
