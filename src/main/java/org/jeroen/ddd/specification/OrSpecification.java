package org.jeroen.ddd.specification;

public class OrSpecification<T> extends ComposableSpecification<T> {
    private final Specification<T> lhs;
    private final Specification<T> rhs;

    public OrSpecification(Specification<T> lhs, Specification<T> rhs) {
        super();
        this.lhs = lhs;
        this.rhs = rhs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfiedBy(T candidate) {
        return lhs.isSatisfiedBy(candidate) || rhs.isSatisfiedBy(candidate);
    }

    public Specification<T> getLhs() {
        return lhs;
    }

    public Specification<T> getRhs() {
        return rhs;
    }

}
