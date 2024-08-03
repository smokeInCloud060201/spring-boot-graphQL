package vn.com.smoke.springgraphql.custom;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import vn.com.smoke.springgraphql.dto.FilterAbstractQuery;
import vn.com.smoke.springgraphql.dto.IQuery;
import vn.com.smoke.springgraphql.entity.BaseEntity;
import vn.com.smoke.springgraphql.util.SpecificationUtil;

import java.util.ArrayList;
import java.util.List;

public class CustomSpecification<E extends BaseEntity, F extends IQuery.Filter,
        O extends IQuery.Order> implements Specification<E> {

    private final FilterAbstractQuery<F, O> filterDTO;


    public CustomSpecification(FilterAbstractQuery<F, O> filterDTO) {
        this.filterDTO = filterDTO;
    }


    @Override
    public Predicate toPredicate(Root<E> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        List<Predicate> predicates = new ArrayList<>();


        if (filterDTO.getAnd() != null) {
            for (F andFilter : filterDTO.getAnd()) {
                predicates.add(SpecificationUtil.createPredicate(andFilter, root, criteriaBuilder));
            }
        }

        if (filterDTO.getOr() != null) {
            for (F orFilter : filterDTO.getOr()) {
                predicates.add(SpecificationUtil.createPredicate(orFilter, root, criteriaBuilder));
            }
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }
}
