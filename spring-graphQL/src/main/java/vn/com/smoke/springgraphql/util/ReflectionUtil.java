package vn.com.smoke.springgraphql.util;

import vn.com.smoke.springgraphql.dto.graphqlquery.expression.impl.BooleanExpression;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.impl.IntegerExpression;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.impl.LocalDateExpression;
import vn.com.smoke.springgraphql.dto.graphqlquery.expression.impl.StringExpression;
import vn.com.smoke.springgraphql.entity.BaseEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class ReflectionUtil {

    public static <T> Field[] getFieldOfClass(Class<T> clazz) {
        return clazz.getDeclaredFields();
    }

    public static <T, U extends BaseEntity> Predicate createPredicate(T filter, Root<U> root, CriteriaBuilder cb, Class<T> clazz) {
        Field[] fields = getFieldOfClass(clazz);
        Predicate predicate = null;
        try {
            for (Field field : fields) {
                String fieldName = field.getName();
                String getterName = "get" + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
                Method getterMethod = clazz.getMethod(getterName);
                Object fieldValue = getterMethod.invoke(filter);
                Class<?> fieldType = field.getType();
                if (fieldValue != null) {
                    if (fieldType == StringExpression.class) {
                        StringExpression fv  = (StringExpression) fieldValue;
                        predicate = createPredicate(fv, fieldName, root, cb);
                    } else if (fieldType == BooleanExpression.class) {
                        BooleanExpression fv  = (BooleanExpression) fieldValue;
                        predicate = createPredicate(fv, fieldName, root, cb);
                    } else if (fieldType == IntegerExpression.class) {
                        IntegerExpression fv  = (IntegerExpression) fieldValue;
                        predicate = createPredicate(fv, fieldName, root, cb);
                    } else if (fieldType == LocalDateExpression.class) {
                        LocalDateExpression fv  = (LocalDateExpression) fieldValue;
                        predicate = createPredicate(fv, fieldName, root, cb);
                    }
                }
            }
        } catch (Exception e) {
            log.info("Exception while creating predicate");
            return null;
        }
        return predicate;
    }

//    public static Sort getSort (List<PersonQuery.Order> orderList) {
//        Stream<Field> fields = Arrays.stream(getFieldOfClass(PersonQuery.Order.class));
//
//        orderList.stream().map(order -> {
//            fields.filter(f -> f.getName().equalsIgnoreCase(order))
//        })
//    }


    private static  <U extends BaseEntity> Predicate createPredicate(StringExpression expression, String attribute, Root<U> root, CriteriaBuilder cb) {


        Path<String> path = root.get(attribute);

        // operator equals
        if (expression.getEquals() != null) {
            return cb.equal(path, expression.getEquals().getValue());
        }
        // operator notEquals
        else if (expression.getNotEquals() != null) {
            return cb.notEqual(path, expression.getNotEquals().getValue());
        }
        // operator in
        else if (expression.getIn() != null) {
            return path.in(expression.getIn().getValues());
        }
        // operator notIn
        else if (expression.getNotIn() != null) {
            return cb.not(path.in(expression.getNotIn().getValues()));
        }

        // operator isNull
        else if (expression.getIsNull() != null) {
            return cb.isNull(path);
        }

        // operator isNull
        else if (expression.getIsNotNull() != null) {
            return cb.isNotNull(path);
        }

        // operator like
        else if (expression.getLike() != null) {
            return cb.like(path, expression.getLike().getValue());
        }
        // operator notLike
        else if (expression.getNotLike() != null) {
            return cb.notLike(path, expression.getNotLike().getValue());
        }

        // if not operation provided
        throw new RuntimeException("No operation provided");
    }

    private static <U extends BaseEntity> Predicate createPredicate(BooleanExpression expression, String attribute, Root<U> root, CriteriaBuilder cb) {

        // one expression object is expected contain exacly one operation
        // i.e. exactly one non null value

        Path<Boolean> path = root.get(attribute);
        // operator equals
        if (expression.getEquals() != null) {
            return cb.equal(path, expression.getEquals().getValue());
        }
        // operator notEquals
        else if (expression.getNotEquals() != null) {
            return path.in(expression.getNotEquals().getValue());
        }
        // operator in
        else if (expression.getIn() != null) {
            return path.in(expression.getIn().getValues());
        }
        // operator notIn
        else if (expression.getNotIn() != null) {
            return cb.not(path.in(expression.getNotIn().getValues()));
        }

        // operator isNull
        else if (expression.getIsNull() != null) {
            return cb.isNull(path);
        }

        // operator isNull
        else if (expression.getIsNotNull() != null) {
            return cb.isNotNull(path);
        }

        // if not operation provided
        throw new RuntimeException("No operation provided");
    }

    private static <U extends BaseEntity> Predicate createPredicate(IntegerExpression expression, String attribute, Root<U> root, CriteriaBuilder cb) {

        // one expression object is expected contain exacly one operation
        // i.e. exactly one non null value

        Path<Integer> path = root.get(attribute);
        // operator equals
        if (expression.getEquals() != null) {
            return cb.equal(path, expression.getEquals().getValue());
        }
        // operator in
        else if (expression.getIn() != null) {
            return path.in(expression.getIn().getValues());
        }
        // operator greaterThan
        else if (expression.getGreaterThan() != null) {
            return cb.greaterThan(path, expression.getGreaterThan().getValue());
        }
        // operator greaterThanOrEqualTo
        else if (expression.getGreaterThanOrEqualTo() != null) {
            return cb.greaterThanOrEqualTo(path, expression.getGreaterThanOrEqualTo().getValue());
        }
        // operator lessThan
        else if (expression.getLessThan() != null) {
            return cb.lessThan(path, expression.getLessThan().getValue());
        }
        // operator lessThanOrEqualTo
        else if (expression.getLessThanOrEqualTo() != null) {
            return cb.lessThanOrEqualTo(path, expression.getLessThanOrEqualTo().getValue());
        }

        // if not operation provided
        throw new RuntimeException("No operation provided");
    }

    private static <U extends BaseEntity> Predicate createPredicate(LocalDateExpression expression, String attribute, Root<U> root, CriteriaBuilder cb) {

        // one expression object is expected contain exacly one operation
        // i.e. exactly one non null value

        Path<LocalDate> path = root.get(attribute);
        // operator equals
        if (expression.getEquals() != null) {
            System.out.println(expression.getEquals().getValue().getClass().getName());
            System.out.println(expression.getEquals().getValue());
            return cb.equal(path, expression.getEquals().getValue());
        }
        // operator notEquals
        else if (expression.getNotEquals() != null) {
            System.out.println(expression.getNotEquals().getValue().getClass().getName());
            System.out.println(expression.getNotEquals().getValue());
            return cb.notEqual(path, expression.getNotEquals().getValue());
        }
        // operator in
        else if (expression.getIn() != null) {
            return path.in(expression.getIn().getValues());
        }
        // operator notIn
        else if (expression.getNotIn() != null) {
            return cb.not(path.in(expression.getNotIn().getValues()));
        }


        // operator greaterThan
        else if (expression.getGreaterThan() != null) {
            return cb.greaterThan(path, expression.getGreaterThan().getValue());
        }
        // operator greaterThanOrEqualTo
        else if (expression.getGreaterThanOrEqualTo() != null) {
            return cb.greaterThanOrEqualTo(path, expression.getGreaterThanOrEqualTo().getValue());
        }
        // operator lessThan
        else if (expression.getLessThan() != null) {
            return cb.lessThan(path, expression.getLessThan().getValue());
        }
        // operator lessThanOrEqualTo
        else if (expression.getLessThanOrEqualTo() != null) {
            return cb.lessThanOrEqualTo(path, expression.getLessThanOrEqualTo().getValue());
        }

        // operator isNull
        else if (expression.getIsNull() != null) {
            return cb.isNull(path);
        }

        // operator isNull
        else if (expression.getIsNotNull() != null) {
            return cb.isNotNull(path);
        }

        // if not operation provided
        throw new RuntimeException("No operation provided");
    }

}
