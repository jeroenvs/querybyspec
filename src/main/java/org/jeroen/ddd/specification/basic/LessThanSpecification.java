package org.jeroen.ddd.specification.basic;

/**
 * Determine if a property value is less than the specified value.
 * 
 * @author Jeroen van Schagen
 * @since 5-1-2011
 *
 * @param <T> type of candidates being checked
 */
public class LessThanSpecification<T> extends CompareToSpecification<T> {
    private static final int LESS_THAN_COMPARISON = -1;

    /**
     * Construct a new {@link LessThanSpecification}.
     * @param property determines what property should be verified
     * @param value candidates are only matched when their property value is beneat this value
     */
    public LessThanSpecification(String property, Object value) {
        super(property, value, LESS_THAN_COMPARISON);
    }

}
