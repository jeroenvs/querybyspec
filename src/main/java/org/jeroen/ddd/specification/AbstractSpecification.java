package org.jeroen.ddd.specification;

/**
 * Specification that can combine its logic with additional specifications.
 * 
 * @author Jeroen van Schagen
 * @since 29-12-2010
 *
 * @param <T> type of candidates being checked
 */
public abstract class AbstractSpecification<T> implements Specification<T> {

    /**
     * 
     * @param rhs
     * @return
     */
    public AndSpecification<T> and(Specification<T> rhs) {
        return new AndSpecification<T>(this, rhs);
    }

    /**
     * 
     * @param rhs
     * @return
     */
    public OrSpecification<T> or(Specification<T> rhs) {
        return new OrSpecification<T>(this, rhs);
    }

    /**
     * 
     * @return
     */
    public NotSpecification<T> not() {
        return new NotSpecification<T>(this);
    }

}
