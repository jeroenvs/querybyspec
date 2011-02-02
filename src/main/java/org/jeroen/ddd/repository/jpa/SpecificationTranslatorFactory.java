package org.jeroen.ddd.repository.jpa;

/**
 * Provides construction logic for {@link SpecificationTranslator} instances. The
 * construction of a translator can be quite difficult, as the various converters have
 * to be registered. By providing a factory, users can remain unaware of this construction.
 * 
 * @author Jeroen van Schagen
 * @since 30-12-2010
 */
public interface SpecificationTranslatorFactory {

    /**
     * Construct a new {@link SpecificationTranslator} with default converters.
     * @return new translator instance with all default converters
     */
    SpecificationTranslator createWithDefaultConverters();

    /**
     * Construct a new {@link SpecificationTranslator} with default converters and
     * annotated converter instances. Use this method to quickly register all desired
     * converters, minimizing the configuration process of our translator.
     * @param basePackage the base package of our custom converters
     * @return translator instance with all default and annotated converters
     */
    SpecificationTranslator createWithAnnotatedConverters(String basePackage);

}
