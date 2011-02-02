package org.jeroen.ddd.specification;

import java.util.Date;
import java.util.GregorianCalendar;

import org.jeroen.ddd.domain.Post;
import org.jeroen.ddd.specification.basic.GreaterThanSpecification;
import org.junit.Assert;
import org.junit.Test;

public class GreaterThanSpecificationTest {
    private final Date minimalDate = new GregorianCalendar(2011, 1, 1).getTime();

    @Test
    public void testGreater() {
        Date greaterDate = new GregorianCalendar(2011, 1, 10).getTime();
        Post post = new Post().setCreationDate(greaterDate);
        Assert.assertTrue(greaterDate.after(minimalDate));
        Assert.assertTrue(new GreaterThanSpecification<Post>("creationDate", minimalDate).isSatisfiedBy(post));
    }

    @Test
    public void testEqual() {
        Post post = new Post().setCreationDate(minimalDate);
        Assert.assertFalse(new GreaterThanSpecification<Post>("creationDate", minimalDate).isSatisfiedBy(post));
    }

    @Test
    public void testLesser() {
        Date lesserDate = new GregorianCalendar(2010, 1, 10).getTime();
        Post post = new Post().setCreationDate(lesserDate);
        Assert.assertFalse(lesserDate.after(minimalDate));
        Assert.assertFalse(new GreaterThanSpecification<Post>("creationDate", minimalDate).isSatisfiedBy(post));
    }

}
