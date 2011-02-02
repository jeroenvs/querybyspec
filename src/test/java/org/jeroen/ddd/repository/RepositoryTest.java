package org.jeroen.ddd.repository;

import java.util.Arrays;

import org.jeroen.ddd.domain.Post;
import org.jeroen.ddd.specification.Specification;
import org.jeroen.ddd.specification.basic.EqualitySpecification;
import org.junit.Assert;
import org.junit.Test;

public class RepositoryTest {

    @Test
    public void testInMemory() {
        Post firstPost = new Post().setMessage("first");
        Post secondPost = new Post().setMessage("second");
        GenericRepository<Post> posts = new GenericRepository<Post>(new InMemoryRepositoryStrategy<Post>());
        Specification<Post> isFirst = new EqualitySpecification<Post>("message", "first");
        // Repository is empty, thus no matching entities should be found
        Assert.assertTrue(posts.matching(isFirst).isEmpty());
        // Append our 'first' and 'second' post to the repository, enabling them to be found
        posts.addAll(Arrays.asList(firstPost, secondPost));
        // Now the 'first' post should be found because it satisfies our equality specification
        Assert.assertEquals(Arrays.asList(firstPost), posts.matching(isFirst));
    }

}
