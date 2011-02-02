package org.jeroen.ddd.repository.jpa;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Indidates that a specification converter will be registered automatically.
 * @author Jeroen van Schagen
 */
@Target(value = { ElementType.TYPE })
public @interface Registered {
    // No internal logic required, just used to attach meta-data to our converters
}
