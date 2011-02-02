package org.jeroen.ddd.specification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Utility class for working with specifications.
 * 
 * @author Jeroen van Schagen
 * @since 29-12-2010
 */
public final class SpecificationUtils {

    /**
     * See if all candidates statisfy the requirements describes in our specification.
     * @param candidates
     * @param <T> type of candidates, checked in our specification
     * @return {@code true} if all candidates statisfy our specification, else {@code false}
     */
    public static <T> boolean isSatisfiedByAll(Specification<T> specification, Collection<T> candidates) {
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
     * @param <T> type of candidates, checked in our specification
     * @return only the candidates that satisfy our specification
     */
    public static <T> List<T> selectMatching(Specification<T> specification, Collection<T> candidates) {
        List<T> result = new ArrayList<T>();
        for (T candidate : candidates) {
            if (specification.isSatisfiedBy(candidate)) {
                result.add(candidate);
            }
        }
        return result;
    }

    // Hide constructor to prevent initialization
    private SpecificationUtils() {
        super();
    }

}
