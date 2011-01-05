package org.jeroen.ddd.specification;

public abstract class CompareSpecification<T> extends ValueBoundSpecification<T> {
    private final int expectedCompare;

    protected CompareSpecification(String property, Object value, int expectedCompare) {
        super(property, value);
        this.expectedCompare = expectedCompare;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    protected final boolean isSatisfyingValue(Object candidateValue) {
        if (candidateValue instanceof Comparable<?>) {
            Comparable<Object> comparable = (Comparable<Object>) candidateValue;
            return comparable.compareTo(getValue()) == expectedCompare;
        } else {
            String message = String.format("Property '%s' is not comparable.", getProperty());
            throw new IllegalStateException(message);
        }
    }

}
