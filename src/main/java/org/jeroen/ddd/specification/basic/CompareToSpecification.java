package org.jeroen.ddd.specification.basic;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.jeroen.ddd.specification.ValueBoundSpecification;

public abstract class CompareToSpecification<T> extends ValueBoundSpecification<T> {
    private final int expectedComparison;

    public CompareToSpecification(String property, Object value, int expectedComparison) {
        super(property, value);
        this.expectedComparison = expectedComparison;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected final boolean isSatisfyingValue(Object candidateValue) {
        return new CompareToBuilder().append(candidateValue, this.getValue()).toComparison() == expectedComparison;
    }
}
