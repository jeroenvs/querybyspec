package org.jeroen.ddd.specification;

import java.lang.reflect.Field;

import org.springframework.util.ReflectionUtils;

public abstract class ValueBoundSpecification<T> extends ComposableSpecification<T> {
    private final String property;
    private final Object expectedValue;

    public ValueBoundSpecification(String property, Object expectedValue) {
        super();
        this.property = property;
        this.expectedValue = expectedValue;
    }

    public String getProperty() {
        return property;
    }

    public Object getExpectedValue() {
        return expectedValue;
    }

    /**
     * Retrieve the actual property value of a candidate instance.
     * We find and retrieve the value by using reflection.
     * @param candidate the candidate instance to retrieve from
     * @return our candidate's current property value
     */
    protected Object getCandidateValue(T candidate) {
        Field field = ReflectionUtils.findField(candidate.getClass(), property);
        ReflectionUtils.makeAccessible(field);
        return ReflectionUtils.getField(field, candidate);
    }

}
