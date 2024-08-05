package vn.com.smoke.springgraphql.dto;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public interface IQuery {

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    class Order {}

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    class Filter {}

}
