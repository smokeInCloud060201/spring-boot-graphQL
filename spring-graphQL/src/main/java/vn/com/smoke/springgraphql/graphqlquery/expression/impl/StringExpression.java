package vn.com.smoke.springgraphql.graphqlquery.expression.impl;

import com.example.graphqldynamicquery.dto.graphqlquery.expression.EquableExpression;
import com.example.graphqldynamicquery.dto.graphqlquery.expression.NullCheckableExpression;
import com.example.graphqldynamicquery.dto.graphqlquery.operator.*;
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
public class StringExpression implements EquableExpression<String>, NullCheckableExpression {

    private Equals<String> equals;
    private NotEquals<String> notEquals;
    private In<String> in;
    private NotIn<String> notIn;

    private IsNull isNull;
    private IsNotNull isNotNull;

    private Like like;
    private NotLike notLike;
}
