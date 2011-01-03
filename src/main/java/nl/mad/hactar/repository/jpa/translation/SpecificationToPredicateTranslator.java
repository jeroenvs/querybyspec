package nl.mad.hactar.repository.jpa.translation;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import nl.mad.hactar.specification.Specification;

/**
 * Capable of translating domain specific {@link Specification} instances, into a query {@link Predicate}.
 * Translating specifications into query criteria enables us to construct, and execute, high performance
 * queries, that still capture the logic of our domain related specification.
 * 
 * @author Jeroen van Schagen
 * @since 28-12-2010
 */
public interface SpecificationToPredicateTranslator {

    /**
     * Translate some {@link Specification} into a new {@link Predicate}, enforcing the same selection criteria
     * as our provided specification. Returned predicates can only be used on the provided criteria query.
     * 
     * @param <T> type of the entities being described in our specification
     * @param specification describes the business logic that we should provide in our predicate
     * @param root path to the root of our query, naturally this path has to be created by the query
     * @param cq the query that will hold our returned predicate, can be used to make subqueries
     * @param cb builder instance used to construct new predicates
     * @return new predicate that enforces our specification logic
     */
    <T> Predicate translateToPredicate(Specification<T> specification, Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb);

    /**
     * Register a converter, enabling it to be used during the conversion of a specification. Whenever a
     * specification is being converted, ensure that some matching converter instance has been registered.
     * 
     * @param converter new converter instance that should be registered
     * @return the manager instance, used to enable chaining
     */
    SpecificationToPredicateTranslatorImpl registerConverter(SpecificationToPredicateConverter<?, ?> converter);

}
