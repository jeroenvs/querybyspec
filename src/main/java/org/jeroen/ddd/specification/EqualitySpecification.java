package org.jeroen.ddd.specification;

import org.apache.commons.lang.ObjectUtils;

public class EqualitySpecification<T> extends ValueBoundSpecification<T> {

    public EqualitySpecification(String property, Object value) {
        super(property, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isSatisfyingValue(Object candidate) {
        return ObjectUtils.equals(candidate, getValue());
    }

}
