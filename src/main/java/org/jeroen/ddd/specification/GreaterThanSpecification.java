package org.jeroen.ddd.specification;

public class GreaterThanSpecification<T> extends CompareSpecification<T> {
    private static final int GREATER_THAN_COMPARISON = 1;

    public GreaterThanSpecification(String property, Object expectedValue) {
        super(property, expectedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isCompareSatisfying(int compare) {
        return compare == GREATER_THAN_COMPARISON;
    }

}
