package nl.mad.hactar.specification;

/**
 * Controls the construction of specifications.
 * 
 * @author Jeroen van Schagen
 * @since 23-12-2010
 */
public class SpecificationBuilder {

    public static <T> ComposableSpecification<T> equal(String property, Object value) {
        return new EqualSpecification<T>(property, value);
    }

    public static <T> ComposableSpecification<T> not(Specification<T> specification) {
        return new NotSpecification<T>(specification);
    }

}
