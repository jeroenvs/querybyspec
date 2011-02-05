package org.jeroen.ddd.specification;

public abstract class CompositeSpecification<T> extends AbstractSpecification<T> {
    private final Specification<T> lhs;
    private final Specification<T> rhs;

    public CompositeSpecification(Specification<T> lhs, Specification<T> rhs) {
        super();
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Specification<T> getLhs() {
        return lhs;
    }

    public Specification<T> getRhs() {
        return rhs;
    }

}
