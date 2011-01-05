package org.jeroen.ddd.specification;

/**
 * Determine if a property value is greater than the specified value.
 * 
 * @author Jeroen van Schagen
 * @since 5-1-2011
 *
 * @param <T> type of entity being checked
 */
public class GreaterThanSpecification<T> extends CompareSpecification<T> {
    private static final int GREATER_THAN_COMPARISON = 1;

    /**
     * Construct a new {@link GreaterThanSpecification}.
     * @param property determines what property should be checked
     * @param value candiates are only matched when they property value is above this value
     */
    public GreaterThanSpecification(String property, Object value) {
        super(property, value, GREATER_THAN_COMPARISON);
    }

}
