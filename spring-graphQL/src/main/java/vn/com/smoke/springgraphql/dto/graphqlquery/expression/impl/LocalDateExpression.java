package vn.com.smoke.springgraphql.dto.graphqlquery.expression.impl;

import vn.com.smoke.springgraphql.dto.graphqlquery.expression.ComparableExpression;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.EquableExpression;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.NullCheckableExpression;
import vn.com.smoke.springgraphql.dto.graphqlquery.operator.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LocalDateExpression implements EquableExpression<LocalDate>, ComparableExpression<LocalDate>, NullCheckableExpression {

    private Equals<LocalDate> equals;
    private NotEquals<LocalDate> notEquals;
    private In<LocalDate> in;
    private NotIn<LocalDate> notIn;

    private GreaterThan<LocalDate> greaterThan;
    private GreaterThanOrEqualTo<LocalDate> greaterThanOrEqualTo;
    private LessThan<LocalDate> lessThan;
    private LessThanOrEqualTo<LocalDate> lessThanOrEqualTo;

    private IsNull isNull;
    private IsNotNull isNotNull;
}
