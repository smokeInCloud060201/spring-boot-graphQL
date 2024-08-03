package vn.com.smoke.springgraphql.util;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import vn.com.smoke.springgraphql.dto.IQuery;
import vn.com.smoke.springgraphql.dto.graphqlquery.OrderType;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.impl.BooleanExpression;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.impl.IntegerExpression;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.impl.LocalDateExpression;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.impl.StringExpression;
import vn.com.smoke.springgraphql.entity.BaseEntity;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class SpecificationUtil {

    public static <U extends BaseEntity, F extends IQuery.Filter> Predicate createPredicate(F filter, Root<U> root, CriteriaBuilder cb) {
        Class<?> clazz = filter.getClass();
        List<Predicate> predicates = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        Class<?>[] declaredClasses = filter.getClass().getDeclaredClasses();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object fieldValue = field.get(filter);
                if (fieldValue != null) {
                    if (fieldValue instanceof StringExpression se) {
                        predicates.add(createPredicate(se, fieldName, root, cb));
                    } else if (fieldValue instanceof BooleanExpression bx) {
                        predicates.add(createPredicate(bx, fieldName, root, cb));
                    } else if (fieldValue instanceof IntegerExpression ix) {
                        predicates.add(createPredicate(ix, fieldName, root, cb));
                    } else if (fieldValue instanceof LocalDateExpression lde) {
                        predicates.add(createPredicate(lde, fieldName, root, cb));
                    }
                }
            } catch (Exception e) {
                log.error("Exception while creating predicate", e);
            }
        }

        return cb.and(predicates.toArray(new Predicate[0]));
    }

    private static Predicate createPredicate(StringExpression expression, String fieldName, Root<?> root, CriteriaBuilder cb) {
        if (expression.getEquals() != null) {
            return cb.equal(root.get(fieldName), expression.getEquals().getValue());
        } else if (expression.getNotEquals() != null) {
            return cb.notEqual(root.get(fieldName), expression.getNotEquals().getValue());
        } else if (expression.getIn() != null) {
            return root.get(fieldName).in(expression.getIn().getValues());
        } else if (expression.getNotIn() != null) {
            return cb.not(root.get(fieldName).in(expression.getNotIn().getValues()));
        } else if (expression.getLike() != null) {
            return cb.like(root.get(fieldName), expression.getLike().getValue());
        } else if (expression.getNotLike() != null) {
            return cb.notLike(root.get(fieldName), expression.getNotLike().getValue());
        }
        return cb.conjunction();
    }

    private static Predicate createPredicate(BooleanExpression expression, String fieldName, Root<?> root, CriteriaBuilder cb) {
        if (expression.getEquals() != null) {
            return cb.equal(root.get(fieldName), expression.getEquals().getValue());
        } else if (expression.getNotEquals() != null) {
            return cb.notEqual(root.get(fieldName), expression.getNotEquals().getValue());
        }
        return cb.conjunction();
    }

    private static Predicate createPredicate(IntegerExpression expression, String fieldName, Root<?> root, CriteriaBuilder cb) {
        if (expression.getEquals() != null) {
            return cb.equal(root.get(fieldName), expression.getEquals().getValue());
        } else if (expression.getNotEquals() != null) {
            return cb.notEqual(root.get(fieldName), expression.getNotEquals().getValue());
        } else if (expression.getGreaterThan() != null) {
            return cb.greaterThan(root.get(fieldName), expression.getGreaterThan().getValue());
        } else if (expression.getLessThan() != null) {
            return cb.lessThan(root.get(fieldName), expression.getLessThan().getValue());
        } else if (expression.getGreaterThanOrEqualTo() != null) {
            return cb.greaterThanOrEqualTo(root.get(fieldName), expression.getGreaterThanOrEqualTo().getValue());
        } else if (expression.getLessThanOrEqualTo() != null) {
            return cb.lessThanOrEqualTo(root.get(fieldName), expression.getLessThanOrEqualTo().getValue());
        }
        return cb.conjunction();
    }

    private static Predicate createPredicate(LocalDateExpression expression, String fieldName, Root<?> root, CriteriaBuilder cb) {
        if (expression.getEquals() != null) {
            return cb.equal(root.get(fieldName), expression.getEquals().getValue());
        } else if (expression.getNotEquals() != null) {
            return cb.notEqual(root.get(fieldName), expression.getNotEquals().getValue());
        } else if (expression.getGreaterThan() != null) {
            return cb.greaterThan(root.get(fieldName), expression.getGreaterThan().getValue());
        } else if (expression.getLessThan() != null) {
            return cb.lessThan(root.get(fieldName), expression.getLessThan().getValue());
        } else if (expression.getGreaterThanOrEqualTo() != null) {
            return cb.greaterThanOrEqualTo(root.get(fieldName), expression.getGreaterThanOrEqualTo().getValue());
        } else if (expression.getLessThanOrEqualTo() != null) {
            return cb.lessThanOrEqualTo(root.get(fieldName), expression.getLessThanOrEqualTo().getValue());
        }
        return cb.conjunction();
    }

//    public static <T extends FilterAbstractQuery, U extends BaseEntity> Specification<U> createSpecification(FilterAbstractQuery filterQuery, Class<U> entityClass) {
//        return (root, query, cb) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            if (filterQuery.getAnd() != null) {
//                Predicate andPredicate = filterQuery.getAnd().stream()
//                        .map(filter -> createPredicate(filter, root, cb))
//                        .reduce(cb::and)
//                        .orElse(cb.conjunction());
//                predicates.add(andPredicate);
//            }
//
//            if (filterQuery.getOr() != null) {
//                Predicate orPredicate = filterQuery.getOr().stream()
//                        .map(filter -> createPredicate(filter, root, cb))
//                        .reduce(cb::or)
//                        .orElse(cb.disjunction());
//                predicates.add(orPredicate);
//            }
//
//            return cb.and(predicates.toArray(new Predicate[0]));
//        };
//    }

    public static <O> Sort createSort(O order) {
        if (order == null) {
            return Sort.unsorted();
        }

        List<Sort.Order> orders = new ArrayList<>();
        Field[] fields = order.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                field.setAccessible(true);
                OrderType orderType = (OrderType) field.get(order);
                if (orderType != null) {
                    Sort.Direction direction = (orderType == OrderType.ASC) ? Sort.Direction.ASC : Sort.Direction.DESC;
                    orders.add(new Sort.Order(direction, field.getName()));
                }
            } catch (Exception e) {
                log.error("Exception while creating sort order", e);
            }
        }

        return Sort.by(orders);
    }
}
