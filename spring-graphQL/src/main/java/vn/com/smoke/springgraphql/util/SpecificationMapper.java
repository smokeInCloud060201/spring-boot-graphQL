package vn.com.smoke.springgraphql.util;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import vn.com.smoke.springgraphql.dto.graphqlquery.OrderType;
import vn.com.smoke.springgraphql.dto.person.PersonQuery;
import vn.com.smoke.springgraphql.entity.Person;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


@Slf4j
public class SpecificationMapper {

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

    public static Sort createSort(PersonQuery.Order order) {
        if (order == null) {
            return Sort.unsorted();
        }
        Class<?> clazz = order.getClass();
        Field[] fields = ReflectionUtil.getFieldOfClass(clazz);
        List<Sort.Order> orders = new ArrayList<>();
        for (Field field : fields) {
            try {
                String fieldName = field.getName();
                String getterName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                Method getterMethod = clazz.getMethod(getterName);
                OrderType fieldValue = (OrderType) getterMethod.invoke(field);
                Class<?> fieldType = field.getType();
                if (fieldValue!= null && fieldType == OrderType.class) {
                    Sort.Direction direction;
                    if (fieldValue == OrderType.ASC) {
                        direction = Sort.Direction.ASC;
                    } else {
                        direction = Sort.Direction.DESC;
                    }
                    orders.add(new Sort.Order(direction, fieldName));
                }
            } catch (Exception e) {
                log.info("Error processing {}", e.getMessage());
            }
        }
        return Sort.by(orders);
    }

}
