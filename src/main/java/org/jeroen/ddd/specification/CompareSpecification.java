package org.jeroen.ddd.specification;

public abstract class CompareSpecification<T> extends ValueBoundSpecification<T> {
    private final int expectedComparison;

    // Made protected to prevent people from directly using this class
    protected CompareSpecification(String property, Object value, int expectedComparison) {
        super(property, value);
        this.expectedComparison = expectedComparison;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final boolean isSatisfyingValue(Object candidate) {
        if (candidate instanceof Comparable<?>) {
            return ((Comparable<Object>) candidate).compareTo(getValue()) == expectedComparison;
        } else {
            throw new IllegalStateException(String.format("Property '%s' is not comparable.", getProperty()));
        }
    }

}
