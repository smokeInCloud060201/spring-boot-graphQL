package vn.com.smoke.springgraphql.graphqlquery.expression.impl;

import com.example.graphqldynamicquery.dto.graphqlquery.expression.ComparableExpression;
import com.example.graphqldynamicquery.dto.graphqlquery.expression.EquableExpression;
import com.example.graphqldynamicquery.dto.graphqlquery.expression.NullCheckableExpression;
import com.example.graphqldynamicquery.dto.graphqlquery.operator.Equals;
import com.example.graphqldynamicquery.dto.graphqlquery.operator.GreaterThan;
import com.example.graphqldynamicquery.dto.graphqlquery.operator.GreaterThanOrEqualTo;
import com.example.graphqldynamicquery.dto.graphqlquery.operator.In;
import com.example.graphqldynamicquery.dto.graphqlquery.operator.IsNotNull;
import com.example.graphqldynamicquery.dto.graphqlquery.operator.IsNull;
import com.example.graphqldynamicquery.dto.graphqlquery.operator.LessThan;
import com.example.graphqldynamicquery.dto.graphqlquery.operator.LessThanOrEqualTo;
import com.example.graphqldynamicquery.dto.graphqlquery.operator.NotEquals;
import com.example.graphqldynamicquery.dto.graphqlquery.operator.NotIn;
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
