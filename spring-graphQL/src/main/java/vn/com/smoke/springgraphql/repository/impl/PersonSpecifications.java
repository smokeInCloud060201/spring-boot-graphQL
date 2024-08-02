package vn.com.smoke.springgraphql.repository.impl;

import vn.com.smoke.springgraphql.dto.graphqlquery.OrderType;
import vn.com.smoke.springgraphql.dto.graphqlquery.PersonQuery;
import vn.com.smoke.springgraphql.entity.Person;
import vn.com.smoke.springgraphql.util.ReflectionUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class PersonSpecifications {

    public static Specification<Person> createSpecification(PersonQuery personQuery) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Add filters from PersonQuery
            if (personQuery.getAnd() != null) {
                predicates.add(createPredicateAndChain(personQuery.getAnd(), root, cb));
            } else if (personQuery.getOr() != null) {
                predicates.add(createPredicateOrChain(personQuery.getOr(), root, cb));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static Predicate createPredicateAndChain(List<PersonQuery.Filter> filters, Root<Person> root, CriteriaBuilder cb) {
        return filters.stream()
                .map(filter -> ReflectionUtil.createPredicate(filter, root, cb, PersonQuery.Filter.class))
                .reduce(cb::and)
                .orElse(cb.conjunction());
    }

    private static Predicate createPredicateOrChain(List<PersonQuery.Filter> filters, Root<Person> root, CriteriaBuilder cb) {
        return filters.stream()
                .map(filter -> ReflectionUtil.createPredicate(filter, root, cb, PersonQuery.Filter.class))
                .reduce(cb::or)
                .orElse(cb.disjunction());
    }

    public static Sort createSort(List<PersonQuery.Order> orderList) {
        List<Sort.Order> orders = orderList.stream()
                .map(order -> {
                    if (order.getId() != null) {
                        return new Sort.Order(order.getId() == OrderType.ASC ? Sort.Direction.ASC : Sort.Direction.DESC, "id");
                    } else if (order.getName() != null) {
                        return new Sort.Order(order.getName() == OrderType.ASC ? Sort.Direction.ASC : Sort.Direction.DESC, "name");
                    } else if (order.getDateOfBirth() != null) {
                        return new Sort.Order(order.getDateOfBirth() == OrderType.ASC ? Sort.Direction.ASC : Sort.Direction.DESC, "dateOfBirth");
                    } else if (order.getEmailVerified() != null) {
                        return new Sort.Order(order.getEmailVerified() == OrderType.ASC ? Sort.Direction.ASC : Sort.Direction.DESC, "emailVerified");
                    } else if (order.getCreditScore() != null) {
                        return new Sort.Order(order.getCreditScore() == OrderType.ASC ? Sort.Direction.ASC : Sort.Direction.DESC, "creditScore");
                    } else {
                        throw new RuntimeException("No order provided for any attribute");
                    }
                })
                .toList();
        return Sort.by(orders);
    }
}
