package org.jeroen.ddd.repository.jpa.translation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * Indicate that this type should automatically be registered.
 * @author Jeroen van Schagen
 */
@Target(value = { ElementType.TYPE })
public @interface RegisteredAutomatically {

}
