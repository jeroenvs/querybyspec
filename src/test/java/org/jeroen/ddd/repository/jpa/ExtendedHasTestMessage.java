package org.jeroen.ddd.repository.jpa;

import org.jeroen.ddd.domain.Post;
import org.jeroen.ddd.specification.basic.EqualitySpecification;

public class ExtendedHasTestMessage extends EqualitySpecification<Post> {

    public ExtendedHasTestMessage() {
        super("message", "test");
    }

}
