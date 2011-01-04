package org.jeroen.ddd.specification;

public abstract class CompareSpecification<T> extends ValueBoundSpecification<T> {

    public CompareSpecification(String property, Object expectedValue) {
        super(property, expectedValue);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public final boolean isSatisfiedBy(T candidate) {
        Object candidateValue = this.getCandidateValue(candidate);
        if (candidateValue instanceof Comparable<?>) {
            Comparable<Object> comparableValue = (Comparable<Object>) candidateValue;
            return isCompareSatisfying(comparableValue.compareTo(getExpectedValue()));
        } else {
            String message = String.format("Property '%s' is not comparable.", getProperty());
            throw new IllegalStateException(message);
        }
    }

    protected abstract boolean isCompareSatisfying(int compare);

}
