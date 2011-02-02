package org.jeroen.ddd.specification;

import java.lang.reflect.Field;

import org.springframework.util.ReflectionUtils;

/**
 * Specification which bases its evaluation on a specific property value. Extensions from this
 * class are only satisfied, when the {@link #isSatisfyingValue(Object)} evaluates {@code true}.
 * 
 * @param <T> type of candidates being verified
 * 
 * @author Jeroen van Schagen
 * @since 25-12-2010
 */
public abstract class ValueBoundSpecification<T> extends ComposableSpecification<T> {
    private final String property;
    private final Object value;

    /**
     * Construct a new {@link ValueBoundSpecification}.
     * @param property expression of the property being checked
     * @param value the value that our property should conform to
     */
    public ValueBoundSpecification(String property, Object value) {
        super();
        this.property = property;
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean isSatisfiedBy(T candidate) {
        Field field = ReflectionUtils.findField(candidate.getClass(), property);
        ReflectionUtils.makeAccessible(field);
        Object candidateValue = ReflectionUtils.getField(field, candidate);
        return isSatisfyingValue(candidateValue);
    }

    /**
     * See if a property value satisfies all the requirements expressed in this specification.
     * @param candidateValue the property value being verified
     * @return {@code true} if the requirements are satisfied, otherwise {@code false}
     */
    protected abstract boolean isSatisfyingValue(Object candidateValue);

    /**
     * 
     * @return
     */
    public String getProperty() {
        return property;
    }

    /**
     * 
     * @return
     */
    public Object getValue() {
        return value;
    }

}
