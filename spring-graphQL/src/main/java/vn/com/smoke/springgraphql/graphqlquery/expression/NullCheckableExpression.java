package vn.com.smoke.springgraphql.graphqlquery.expression;

import com.example.graphqldynamicquery.dto.graphqlquery.operator.IsNotNull;
import com.example.graphqldynamicquery.dto.graphqlquery.operator.IsNull;

public interface NullCheckableExpression {

    IsNull getIsNull();

    IsNotNull getIsNotNull();
}
