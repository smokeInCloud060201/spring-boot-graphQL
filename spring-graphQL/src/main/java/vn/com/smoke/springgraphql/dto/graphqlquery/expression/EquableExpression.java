package vn.com.smoke.springgraphql.dto.graphqlquery.expression;

import vn.com.smoke.springgraphql.dto.graphqlquery.operator.Equals;
import vn.com.smoke.springgraphql.dto.graphqlquery.operator.In;
import vn.com.smoke.springgraphql.dto.graphqlquery.operator.NotEquals;
import vn.com.smoke.springgraphql.dto.graphqlquery.operator.NotIn;

public interface EquableExpression<T> {

    Equals<T> getEquals();

    NotEquals<T> getNotEquals();

    In<T> getIn();

    NotIn<T> getNotIn();
}
