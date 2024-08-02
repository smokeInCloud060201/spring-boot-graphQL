package vn.com.smoke.springgraphql.dto.graphqlquery.expression.impl;

import vn.com.smoke.springgraphql.dto.graphqlquery.expression.EquableExpression;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.NullCheckableExpression;
import vn.com.smoke.springgraphql.dto.graphqlquery.operator.*;
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
public class BooleanExpression implements EquableExpression<Boolean>, NullCheckableExpression {

    private Equals<Boolean> equals;
    private NotEquals<Boolean> notEquals;
    private In<Boolean> in;
    private NotIn<Boolean> notIn;

    private IsNull isNull;
    private IsNotNull isNotNull;

    private IsTrue isTrue;
    private IsFalse isFalse;
}
