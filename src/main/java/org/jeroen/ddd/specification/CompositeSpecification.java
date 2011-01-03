package org.jeroen.ddd.specification;

/**
 * Specification that is based on two internal specifications.
 * 
 * @author Jeroen van Schagen
 * @since 25-12-2010
 *
 * @param <T> type of domain object being checked
 */
public abstract class CompositeSpecification<T> extends ComposableSpecification<T> {
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
