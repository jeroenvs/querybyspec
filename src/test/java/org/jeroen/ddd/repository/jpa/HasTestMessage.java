package org.jeroen.ddd.repository.jpa;

import org.jeroen.ddd.domain.Post;
import org.jeroen.ddd.specification.ComposableSpecification;


/**
 * Specification that tests if a {@link Post} has the message value 'test'.
 * @author Jeroen van Schagen
 * @since 28-12-2010
 */
public class HasTestMessage extends ComposableSpecification<Post> {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSatisfiedBy(Post candidate) {
        return candidate.getMessage().equals("test");
    }

}
