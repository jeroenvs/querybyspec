package org.jeroen.ddd.specification;

/**
 * In computer programming, the specification pattern is a particular software design
 * pattern, whereby business logic can be recombined by chaining the business logic
 * together using boolean logic.
 * 
 * @author Jeroen van Schagen
 * @since 23-12-2010
 *
 * @param <T> type of candidates being checked
 */
public interface Specification<T> {

    /**
     * See if an object satisfies all the requirements expressed in this specification.
     * @param candidate the object being verified
     * @return {@code true} if the requirements are satisfied, otherwise {@code false}
     */
    boolean isSatisfiedBy(T candidate);

}
