package org.jeroen.ddd.specification;

public class NotSpecification<T> extends ComposableSpecification<T> {
    private final Specification<T> proposition;

    public NotSpecification(Specification<T> proposition) {
        super();
        this.proposition = proposition;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfiedBy(T candidate) {
        return !proposition.isSatisfiedBy(candidate);
    }

    public Specification<T> getProposition() {
        return proposition;
    }

}
