package vn.com.smoke.springgraphql.dto.graphqlquery.expression;

import vn.com.smoke.springgraphql.dto.graphqlquery.operator.IsNotNull;
import vn.com.smoke.springgraphql.dto.graphqlquery.operator.IsNull;

public interface NullCheckableExpression {

    IsNull getIsNull();

    IsNotNull getIsNotNull();
}
