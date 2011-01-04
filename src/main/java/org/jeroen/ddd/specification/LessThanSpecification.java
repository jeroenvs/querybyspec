package org.jeroen.ddd.specification;

public class LessThanSpecification<T> extends CompareSpecification<T> {
    private static final int LESS_THAN_COMPARE = -1;

    public LessThanSpecification(String property, Object expectedValue) {
        super(property, expectedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean isCompareSatisfying(int compare) {
        return compare == LESS_THAN_COMPARE;
    }

}
