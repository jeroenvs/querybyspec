package org.jeroen.ddd.repository.jpa;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jeroen.ddd.domain.Post;
import org.jeroen.ddd.specification.ComposableSpecification;
import org.jeroen.ddd.specification.Specification;
import org.jeroen.ddd.specification.basic.EqualitySpecification;
import org.jeroen.ddd.specification.basic.GreaterThanSpecification;
import org.jeroen.ddd.specification.basic.LessThanSpecification;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testContext.xml" })
public class JpaRepositoryStrategyTest {
    private JpaRepositoryStrategy<Post> posts;

    @PersistenceContext
    private EntityManager em;

    @Resource
    private SpecificationTranslator translator;

    // Entities being used for testing
    private Post post;
    private Post anotherPost;

    @Before
    public void setUp() {
        posts = new JpaRepositoryStrategy<Post>(Post.class);
        posts.setEntityManager(em);
        posts.setTranslator(translator);
        em.persist(post = new Post().setMessage("test"));
        em.persist(anotherPost = new Post().setMessage("another"));
    }

    @Test
    public void testTranslationEqual() {
        Specification<Post> hasTestMessage = new EqualitySpecification<Post>("message", "test");
        List<Post> postList = posts.matching(hasTestMessage);
        Assert.assertEquals(Arrays.asList(post), postList);
    }

    @Test
    public void testTranslationCompositeSpec() {
        ComposableSpecification<Post> hasTestMessage = new EqualitySpecification<Post>("message", "test");
        List<Post> postList = posts.matching(hasTestMessage.not());
        Assert.assertEquals(Arrays.asList(anotherPost), postList);
    }

    @Test
    public void testTranslationExtendedSpec() {
        Specification<Post> hasTestMessage = new ExtendedHasTestMessage();
        List<Post> postList = posts.matching(hasTestMessage);
        Assert.assertEquals(Arrays.asList(post), postList);
    }

    @Test
    public void testTranslateJpaSpec() {
        List<Post> postList = posts.matching(new HasTestMessageJpa());
        Assert.assertEquals(Arrays.asList(post), postList);
    }

    @Test
    public void testTranslatedByCustomConverter() {
        List<Post> postList = posts.matching(new HasTestMessage());
        Assert.assertEquals(Arrays.asList(post), postList);
    }

    // Test converters

    @Test
    public void testLessAndGreaterThan() {
        Date firstDayOfDecember = new GregorianCalendar(2010, 11, 1).getTime();
        Date christmas = new GregorianCalendar(2010, 11, 25).getTime();
        Date newYearsEve = new GregorianCalendar(2010, 11, 31).getTime();
        post.setCreationDate(newYearsEve);
        anotherPost.setCreationDate(firstDayOfDecember);
        // Find the post(s) that were made after the last day of christmas
        Specification<Post> postedAfterChristmas = new GreaterThanSpecification<Post>("creationDate", christmas);
        Assert.assertEquals(Arrays.asList(post), posts.matching(postedAfterChristmas));
        // Find the post(s) that were made before the last day of christmas
        Specification<Post> postedBeforeChristmas = new LessThanSpecification<Post>("creationDate", christmas);
        Assert.assertEquals(Arrays.asList(anotherPost), posts.matching(postedBeforeChristmas));
    }

}
