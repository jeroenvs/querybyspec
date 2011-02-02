package org.jeroen.ddd.repository.jpa;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.jeroen.ddd.domain.Post;
import org.jeroen.ddd.domain.Post_;
import org.jeroen.ddd.repository.jpa.Registered;
import org.jeroen.ddd.repository.jpa.SpecificationConverter;


/**
 * Converts a {@link HasTestMessage} into a {@link Predicate}.
 * @author Jeroen van Schagen
 * @since 28-12-2010
 */
@Registered
public class HasTestMessageConverter implements SpecificationConverter<HasTestMessage, Post> {

    /**
     * {@inheritDoc}
     */
    @Override
    public Predicate convert(HasTestMessage specification, Root<Post> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
        return cb.equal(root.get(Post_.message), "test");
    }

}
