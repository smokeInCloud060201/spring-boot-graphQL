package vn.com.smoke.springgraphql.graphqlquery.operator;

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
public class GreaterThanOrEqualTo<T> {

    private T value;
}
