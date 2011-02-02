package org.jeroen.ddd.repository.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.jeroen.ddd.specification.Specification;

/**
 * Specification capable of translating itself into a Java Persistence API specific
 * {@link Predicate}. The returned predicate enforces a selection criteria on only
 * entities that satisfy the specification's business rules.
 * 
 * @author Jeroen van Schagen
 * @since 28-12-2010
 * 
 * @param <T> type of entity represented in our specification logic
 */
public interface JpaSpecification<T> extends Specification<T> {

    /**
     * Construct a {@link Predicate} that enforces this specification's criteria.
     * 
     * @param root path to the root of our query
     * @param cq query that will hold our specification criteria
     * @param cb criteria builder, used to construct predicates
     * @return the converted predicate
     */
    Predicate toPredicate(Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb);

}
