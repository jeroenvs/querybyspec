package org.jeroen.ddd.specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for specifications.
 * @author Jeroen van Schagen
 * @since 2-2-2011
 */
public final class Specifications {

    /**
     * 
     * @param <T>
     * @param specification
     * @param candidates
     * @return
     */
    public static <T> boolean isSatisfiedByAll(Specification<T> specification, Iterable<T> candidates) {
        for (T candidate : candidates) {
            if (!specification.isSatisfiedBy(candidate)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 
     * @param <T>
     * @param specification
     * @param candidates
     * @return
     */
    public static <T> boolean isSatisfiedBySome(Specification<T> specification, Iterable<T> candidates) {
        for (T candidate : candidates) {
            if (specification.isSatisfiedBy(candidate)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 
     * @param <T>
     * @param specification
     * @param candidates
     * @return
     */
    public static <T> List<T> findAllSatisfying(Specification<T> specification, Iterable<T> candidates) {
        List<T> selection = new ArrayList<T>();
        for (T candidate : candidates) {
            if (specification.isSatisfiedBy(candidate)) {
                selection.add(candidate);
            }
        }
        return selection;
    }

    /**
     * 
     * @param <T>
     * @param specification
     * @param candidates
     * @return
     */
    public static <T> long countAllSatisfying(Specification<T> specification, Iterable<T> candidates) {
        long numberOfMatches = 0;
        for (T candidate : candidates) {
            if (specification.isSatisfiedBy(candidate)) {
                numberOfMatches++;
            }
        }
        return numberOfMatches;
    }

    /**
     * 
     * @param <T>
     * @param specification
     * @return
     */
    public static <T> Specification<T> not(Specification<T> specification) {
        return new NotSpecification<T>(specification);
    }

    /**
     * 
     * @param <T>
     * @param lhs
     * @param rhs
     * @return
     */
    public static <T> Specification<T> and(Specification<T> lhs, Specification<T> rhs) {
        return new AndSpecification<T>(lhs, rhs);
    }

    /**
     * 
     * @param <T>
     * @param lhs
     * @param rhs
     * @return
     */
    public static <T> Specification<T> or(Specification<T> lhs, Specification<T> rhs) {
        return new OrSpecification<T>(lhs, rhs);
    }

    // Hidden to prevent initialization
    private Specifications() {
        super();
    }

}
