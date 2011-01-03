package nl.mad.hactar.specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Provides default support for specifications.
 * 
 * @author Jeroen van Schagen
 * @since 29-12-2010
 *
 * @param <T> type of domain object referenced in our specification
 */
public class SpecificationHelper<T> {
    private final Specification<T> specification;

    /**
     * Construct a new {@link SpecificationHelper}.
     * @param specification the specification to provide support for
     */
    public SpecificationHelper(Specification<T> specification) {
        super();
        this.specification = specification;
    }

    /**
     * See if all candidates statisfy the requirements describes in our specification.
     * @param candidates
     * @return {@code true} if all candidates statisfy our specification, else {@code false}
     */
    public boolean isSatisfiedByAll(Collection<T> candidates) {
        boolean satisfied = true;
        for (T candidate : candidates) {
            if (!specification.isSatisfiedBy(candidate)) {
                satisfied = false;
                break;
            }
        }
        return satisfied;
    }

    /**
     * Retrieve all candidates that satisfy the requirements described in our specification.
     * @param candidates
     * @return only the candidates that satisfy our specification
     */
    public List<T> findMatches(Collection<T> candidates) {
        List<T> result = new ArrayList<T>();
        for (T candidate : candidates) {
            if (specification.isSatisfiedBy(candidate)) {
                result.add(candidate);
            }
        }
        return result;
    }

}
