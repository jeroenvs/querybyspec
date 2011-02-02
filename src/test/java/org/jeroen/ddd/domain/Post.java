package org.jeroen.ddd.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Represents a post, which contains a database identifier and string-based message.
 * Used only to test our data access functionality using JPA and ORM technology.
 * @author Jeroen van Schagen
 */
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "creation_date")
    private Date creationDate = new Date();

    /**
     * Retrieve identifier.
     * @return identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Retrieve the entities message.
     * @return current message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Modify the entities message.
     * @param message new message
     * @return the current instance, for chaining
     */
    public Post setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Retrieve the entities creation date.
     * @return current creation date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Modify the creation date.
     * @param creationDate new creation date
     * @return the current instance, for chaining
     */
    public Post setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

}
