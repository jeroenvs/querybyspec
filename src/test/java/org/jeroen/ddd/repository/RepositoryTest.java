package org.jeroen.ddd.repository;

import java.util.Arrays;
import java.util.List;


import org.jeroen.ddd.domain.Post;
import org.jeroen.ddd.repository.Repository;
import org.jeroen.ddd.repository.RepositoryImpl;
import org.jeroen.ddd.specification.EqualSpecification;
import org.junit.Assert;
import org.junit.Test;

public class RepositoryTest {

    @Test
    public void testInMemory() {
        Post firstPost = new Post().setMessage("first");
        Post secondPost = new Post().setMessage("second");
        Repository<Post> postRepository = new RepositoryImpl<Post>(Arrays.asList(firstPost, secondPost));
        List<Post> matchingPosts = postRepository.matching(new EqualSpecification<Post>("message", "first"));
        Assert.assertEquals(Arrays.asList(firstPost), matchingPosts);
    }

}
