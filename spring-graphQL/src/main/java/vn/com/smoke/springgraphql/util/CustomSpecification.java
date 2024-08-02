package vn.com.smoke.springgraphql.util;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import vn.com.smoke.springgraphql.dto.FilterAbstractQuery;
import vn.com.smoke.springgraphql.entity.BaseEntity;

public class CustomSpecification <E extends BaseEntity, T extends FilterAbstractQuery> {
    public CustomSpecification(T filter) {
        this.filter = filter;
    }

    private T filter;

}
