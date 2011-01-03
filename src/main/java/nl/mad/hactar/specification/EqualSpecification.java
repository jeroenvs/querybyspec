package nl.mad.hactar.specification;

import java.lang.reflect.Field;

import org.apache.commons.lang.ObjectUtils;
import org.springframework.util.ReflectionUtils;

public class EqualSpecification<T> extends ComposableSpecification<T> {
    private final String property;
    private final Object value;

    public EqualSpecification(String property, Object value) {
        super();
        this.property = property;
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfiedBy(T candidate) {
        Field field = ReflectionUtils.findField(candidate.getClass(), property);
        ReflectionUtils.makeAccessible(field);
        Object actual = ReflectionUtils.getField(field, candidate);
        return ObjectUtils.equals(actual, value);
    }

    public String getProperty() {
        return property;
    }

    public Object getValue() {
        return value;
    }

}
