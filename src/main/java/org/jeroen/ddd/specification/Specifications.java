package org.jeroen.ddd.specification;

/**
 * Controls the construction of specifications.
 * 
 * @author Jeroen van Schagen
 * @since 23-12-2010
 */
public class Specifications {

    // TODO: Method generics are not so helpful when the arguments have no T type

    public static <T> ComposableSpecification<T> not(Specification<T> proposition) {
        return new NotSpecification<T>(proposition);
    }

    public static <T> ComposableSpecification<T> eq(String property, Object value) {
        return new EqualitySpecification<T>(property, value);
    }

    public static <T> ComposableSpecification<T> gt(String property, Object value) {
        return new GreaterThanSpecification<T>(property, value);
    }

    public static <T> ComposableSpecification<T> ge(String property, Object value) {
        return new GreaterThanSpecification<T>(property, value).or(new EqualitySpecification<T>(property, value));
    }

}
