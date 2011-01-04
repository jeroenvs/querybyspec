package org.jeroen.ddd.specification;

import org.apache.commons.lang.ObjectUtils;

public class EqualitySpecification<T> extends ValueBoundSpecification<T> {

    public EqualitySpecification(String property, Object expectedValue) {
        super(property, expectedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfiedBy(T candidate) {
        return ObjectUtils.equals(getCandidateValue(candidate), getExpectedValue());
    }

}
