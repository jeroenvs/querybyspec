package nl.mad.hactar.specification;

/**
 * Specification that can combine its logic with additional specifications.
 * 
 * @author Jeroen van Schagen
 * @since 29-12-2010
 *
 * @param <T> type of domain object being checked
 */
public abstract class ComposableSpecification<T> implements Specification<T> {

    /**
     * 
     * @param other
     * @return
     */
    public Specification<T> and(Specification<T> other) {
        return new AndSpecification<T>(this, other);
    }

    /**
     * 
     * @param other
     * @return
     */
    public Specification<T> or(Specification<T> other) {
        return new OrSpecification<T>(this, other);
    }

}
