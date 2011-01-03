package org.jeroen.ddd.repository.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.jeroen.ddd.domain.Post;
import org.jeroen.ddd.domain.Post_;
import org.jeroen.ddd.repository.jpa.PredicateSpecification;


/**
 * {@link HasTestMessage} that knows how to construct a corresponding {@link Predicate}.
 * @author Jeroen van Schagen
 * @since 28-12-2010
 */
public class HasTestMessageJpa extends HasTestMessage implements PredicateSpecification<Post> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        return cb.equal(root.get(Post_.message), "test");
    }

}
