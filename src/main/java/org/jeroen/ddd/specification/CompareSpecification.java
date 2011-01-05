package org.jeroen.ddd.specification;

public abstract class CompareSpecification<T> extends ValueBoundSpecification<T> {
    private final int expectedComparison;

    protected CompareSpecification(String property, Object value, int expectedComparison) {
        super(property, value);
        this.expectedComparison = expectedComparison;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final boolean isSatisfyingValue(Object candidateValue) {
        if (candidateValue instanceof Comparable<?>) {
            Comparable<Object> comparable = (Comparable<Object>) candidateValue;
            return comparable.compareTo(getValue()) == expectedComparison;
        } else {
            String message = String.format("Property '%s' is not comparable.", getProperty());
            throw new IllegalStateException(message);
        }
    }

}
