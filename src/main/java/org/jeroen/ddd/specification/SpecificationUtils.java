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

    public static <T> boolean isSatisfiedByAll(Specification<T> specification, Collection<T> candidates) {
        for (T candidate : candidates) {
            if (!specification.isSatisfiedBy(candidate)) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean isSatisfiedBySome(Specification<T> specification, Collection<T> candidates) {
        for (T candidate : candidates) {
            if (specification.isSatisfiedBy(candidate)) {
                return true;
            }
        }
        return false;
    }

    public static <T> List<T> selectMatches(Specification<T> specification, Collection<T> candidates) {
        List<T> result = new ArrayList<T>();
        for (T candidate : candidates) {
            if (specification.isSatisfiedBy(candidate)) {
                result.add(candidate);
            }
        }
        return result;
    }

    public static <T> long countMatches(Specification<T> specification, Collection<T> candidates) {
        long count = 0;
        for (T candidate : candidates) {
            if (specification.isSatisfiedBy(candidate)) {
                count++;
            }
        }
        return count;
    }

    // Hide constructor to prevent initialization
    private SpecificationUtils() {
        super();
    }

}
