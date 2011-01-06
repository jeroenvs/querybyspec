package org.jeroen.ddd.domain;

import java.util.Date;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * Static meta model for our {@link Post} class. This meta model is
 * used to provide fully type-safe query construction.
 * 
 * @author Jeroen van Schagen
 */
@StaticMetamodel(Post.class)
public class Post_ {
    public static volatile SingularAttribute<Post, Long> id;
    public static volatile SingularAttribute<Post, String> message;
    public static volatile SingularAttribute<Post, Date> creationDate;
}
