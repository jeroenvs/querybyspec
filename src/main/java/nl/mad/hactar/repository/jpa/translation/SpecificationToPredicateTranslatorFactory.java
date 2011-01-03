package nl.mad.hactar.repository.jpa.translation;

/**
 * Provides construction logic for {@link SpecificationToPredicateTranslator} instances.
 * The construction of a translator can be quite difficult, as the various converters have
 * to be registered. By providing a factory, users can remain unaware of this construction.
 * 
 * @author Jeroen van Schagen
 * @since 30-12-2010
 */
public interface SpecificationToPredicateTranslatorFactory {

    /**
     * Construct a new {@link SpecificationToPredicateTranslator} with default converters.
     * 
     * @return new translator instance with all default converters
     */
    SpecificationToPredicateTranslator createTranslator();

    /**
     * Construct a new {@link SpecificationToPredicateTranslator} with default converters
     * and annotated converter instances. Use this method to quickly register all desired
     * converters, minimizing the configuration process of our translator.
     * 
     * @param basePackage the base package of our custom converters
     * @return translator instance with all default and annotated converters
     */
    SpecificationToPredicateTranslator createTranslator(String basePackage);

}
