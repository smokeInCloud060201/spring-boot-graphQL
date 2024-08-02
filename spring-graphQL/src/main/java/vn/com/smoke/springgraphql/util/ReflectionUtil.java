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


    private static  <U extends BaseEntity> Predicate createPredicate(StringExpression expression, String attribute, Root<U> root, CriteriaBuilder cb) {


        Path<String> path = root.get(attribute);

        if (expression.getEquals() != null) {
            return cb.equal(path, expression.getEquals().getValue());
        }
        else if (expression.getNotEquals() != null) {
            return cb.notEqual(path, expression.getNotEquals().getValue());
        }
        else if (expression.getIn() != null) {
            return path.in(expression.getIn().getValues());
        }
        else if (expression.getNotIn() != null) {
            return cb.not(path.in(expression.getNotIn().getValues()));
        }

        else if (expression.getIsNull() != null) {
            return cb.isNull(path);
        }

        else if (expression.getIsNotNull() != null) {
            return cb.isNotNull(path);
        }

        else if (expression.getLike() != null) {
            return cb.like(path, expression.getLike().getValue());
        }
        else if (expression.getNotLike() != null) {
            return cb.notLike(path, expression.getNotLike().getValue());
        }

        throw new RuntimeException("No operation provided");
    }

    private static <U extends BaseEntity> Predicate createPredicate(BooleanExpression expression, String attribute, Root<U> root, CriteriaBuilder cb) {

        Path<Boolean> path = root.get(attribute);
        if (expression.getEquals() != null) {
            return cb.equal(path, expression.getEquals().getValue());
        }
        else if (expression.getNotEquals() != null) {
            return path.in(expression.getNotEquals().getValue());
        }
        else if (expression.getIn() != null) {
            return path.in(expression.getIn().getValues());
        }
        else if (expression.getNotIn() != null) {
            return cb.not(path.in(expression.getNotIn().getValues()));
        }

        else if (expression.getIsNull() != null) {
            return cb.isNull(path);
        }

        else if (expression.getIsNotNull() != null) {
            return cb.isNotNull(path);
        }

        throw new RuntimeException("No operation provided");
    }

    private static <U extends BaseEntity> Predicate createPredicate(IntegerExpression expression, String attribute, Root<U> root, CriteriaBuilder cb) {

        Path<Integer> path = root.get(attribute);
        if (expression.getEquals() != null) {
            return cb.equal(path, expression.getEquals().getValue());
        }
        else if (expression.getIn() != null) {
            return path.in(expression.getIn().getValues());
        }
        else if (expression.getGreaterThan() != null) {
            return cb.greaterThan(path, expression.getGreaterThan().getValue());
        }
        else if (expression.getGreaterThanOrEqualTo() != null) {
            return cb.greaterThanOrEqualTo(path, expression.getGreaterThanOrEqualTo().getValue());
        }
        else if (expression.getLessThan() != null) {
            return cb.lessThan(path, expression.getLessThan().getValue());
        }
        else if (expression.getLessThanOrEqualTo() != null) {
            return cb.lessThanOrEqualTo(path, expression.getLessThanOrEqualTo().getValue());
        }

        throw new RuntimeException("No operation provided");
    }

    private static <U extends BaseEntity> Predicate createPredicate(LocalDateExpression expression, String attribute, Root<U> root, CriteriaBuilder cb) {

        Path<LocalDate> path = root.get(attribute);
        if (expression.getEquals() != null) {
            return cb.equal(path, expression.getEquals().getValue());
        }
        else if (expression.getNotEquals() != null) {
            return cb.notEqual(path, expression.getNotEquals().getValue());
        }
        else if (expression.getIn() != null) {
            return path.in(expression.getIn().getValues());
        }
        else if (expression.getNotIn() != null) {
            return cb.not(path.in(expression.getNotIn().getValues()));
        }

        else if (expression.getGreaterThan() != null) {
            return cb.greaterThan(path, expression.getGreaterThan().getValue());
        }
        else if (expression.getGreaterThanOrEqualTo() != null) {
            return cb.greaterThanOrEqualTo(path, expression.getGreaterThanOrEqualTo().getValue());
        }
        else if (expression.getLessThan() != null) {
            return cb.lessThan(path, expression.getLessThan().getValue());
        }
        else if (expression.getLessThanOrEqualTo() != null) {
            return cb.lessThanOrEqualTo(path, expression.getLessThanOrEqualTo().getValue());
        }

        else if (expression.getIsNull() != null) {
            return cb.isNull(path);
        }

        else if (expression.getIsNotNull() != null) {
            return cb.isNotNull(path);
        }

        throw new RuntimeException("No operation provided");
    }

}
