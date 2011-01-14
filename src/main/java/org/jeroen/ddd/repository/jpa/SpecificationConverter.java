package org.jeroen.ddd.repository.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.jeroen.ddd.specification.Specification;

/**
 * Specifies the conversion logic of a {@link Specification} into {@link Predicate}.
 * 
 * @param <T> type of candidates, checked in the specification
 * @param <S> type of specification being converted
 * 
 * @author Jeroen van Schagen
 * @since 28-12-2010
 */
public interface SpecificationConverter<S extends Specification<T>, T> {

    /**
     * Convert some {@link Specification} into a Java Persistence API {@link Predicate}.
     * The returned predicate enforces a selection criteria on only entities that satisfy
     * the specification's business rules.
     * 
     * @param specification our business specification being converted
     * @param root path to the root of our query
     * @param cq query that will hold our specification criteria
     * @param cb criteria builder, used to construct predicates
     * @return our constructed predicate, enforcing only matching entities to be selected
     */
    Predicate convertToPredicate(S specification, Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb);

}
