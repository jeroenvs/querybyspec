package org.jeroen.ddd.specification;

import java.lang.reflect.Field;

import org.springframework.util.ReflectionUtils;

/**
 * 
 * @author Jeroen van Schagen
 * @since 25-12-2010
 *
 * @param <T> type of candidates being checked
 */
public abstract class ValueBoundSpecification<T> extends ComposableSpecification<T> {
    private final String property;
    private final Object value;

    public ValueBoundSpecification(String property, Object value) {
        super();
        this.property = property;
        this.value = value;
    }

    public final boolean isSatisfiedBy(T candidate) {
        Field field = ReflectionUtils.findField(candidate.getClass(), property);
        ReflectionUtils.makeAccessible(field);
        Object candidateValue = ReflectionUtils.getField(field, candidate);
        return isSatisfyingValue(candidateValue);
    }

    protected abstract boolean isSatisfyingValue(Object candidateValue);

    public String getProperty() {
        return property;
    }

    public Object getValue() {
        return value;
    }

}
