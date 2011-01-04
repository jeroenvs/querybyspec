package org.jeroen.ddd.specification;

/**
 * Controls the construction of specifications.
 * 
 * @author Jeroen van Schagen
 * @since 23-12-2010
 */
public class Specifications {

    public static <T> ComposableSpecification<T> eq(String property, Object value) {
        return new EqualitySpecification<T>(property, value);
    }

    public static <T> ComposableSpecification<T> not(Specification<T> proposition) {
        return new NotSpecification<T>(proposition);
    }

}
