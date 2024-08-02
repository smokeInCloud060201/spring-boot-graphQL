package vn.com.smoke.springgraphql.dto.graphqlquery.expression.impl;

import vn.com.smoke.springgraphql.dto.graphqlquery.expression.ComparableExpression;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.EquableExpression;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.NullCheckableExpression;
import vn.com.smoke.springgraphql.dto.graphqlquery.operator.Equals;
import vn.com.smoke.springgraphql.dto.graphqlquery.operator.GreaterThan;
import vn.com.smoke.springgraphql.dto.graphqlquery.operator.GreaterThanOrEqualTo;
import vn.com.smoke.springgraphql.dto.graphqlquery.operator.In;
import vn.com.smoke.springgraphql.dto.graphqlquery.operator.IsNotNull;
import vn.com.smoke.springgraphql.dto.graphqlquery.operator.IsNull;
import vn.com.smoke.springgraphql.dto.graphqlquery.operator.LessThan;
import vn.com.smoke.springgraphql.dto.graphqlquery.operator.LessThanOrEqualTo;
import vn.com.smoke.springgraphql.dto.graphqlquery.operator.NotEquals;
import vn.com.smoke.springgraphql.dto.graphqlquery.operator.NotIn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IntegerExpression implements EquableExpression<Integer>, ComparableExpression<Integer>, NullCheckableExpression {

    private Equals<Integer> equals;
    private NotEquals<Integer> notEquals;
    private In<Integer> in;
    private NotIn<Integer> notIn;
    private GreaterThan<Integer> greaterThan;
    private GreaterThanOrEqualTo<Integer> greaterThanOrEqualTo;
    private LessThan<Integer> lessThan;
    private LessThanOrEqualTo<Integer> lessThanOrEqualTo;
    private IsNull isNull;
    private IsNotNull isNotNull;
}
