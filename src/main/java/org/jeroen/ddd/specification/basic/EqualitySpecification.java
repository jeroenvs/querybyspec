package org.jeroen.ddd.specification.basic;

import org.apache.commons.lang.ObjectUtils;
import org.jeroen.ddd.specification.ValueBoundSpecification;

public class EqualitySpecification<T> extends ValueBoundSpecification<T> {

    public EqualitySpecification(String property, Object value) {
        super(property, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isSatisfyingValue(Object candidateValue) {
        return ObjectUtils.equals(candidateValue, getValue());
    }

}
