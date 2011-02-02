package org.jeroen.ddd.repository.jpa;

import java.io.IOException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

/**
 * Spring oriented implementation of {@link SpecificationTranslatorFactory}.
 * Allows converters to be scanned from the classpath, using a specific base
 * package. Whenever converter classes are found, it will attempt to retrieve
 * a bean from the current application context. In case no corresponding bean
 * is found, a new nullary converter instance is created.
 * 
 * @author Jeroen van Schagen
 * @since 30-12-2010
 */
public class SpecificationTranslatorFactoryImpl extends SpecificationTranslatorFactory implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpecificationTranslator createWithAnnotatedConverters(String basePackage) {
        SpecificationTranslator translator = createWithDefaultConverters();
        // Find all annotated converters in the provided base package
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new IsRegisterableConverterFilter());
        for (BeanDefinition bd : scanner.findCandidateComponents(basePackage)) {
            try {
                // Register each found converter instance
                Class<?> converterClass = Class.forName(bd.getBeanClassName());
                translator.registerConverter(findOrCreateConverter(converterClass));
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(e);
            }
        }
        return translator;
    }

    /**
     * Retrieve converter from application context, or else construct a {@code new} instance.
     * 
     * @param converterClass the type of our converter
     * @return a new or existing converter instance
     */
    private SpecificationConverter<?, ?> findOrCreateConverter(Class<?> converterClass) {
        Object instance = null;
        try {
            instance = applicationContext.getBean(converterClass);
        } catch (NoSuchBeanDefinitionException e) {
            instance = BeanUtils.instantiate(converterClass);
        }
        return (SpecificationConverter<?, ?>) instance;
    }

    // Matches annotated specification-to-predicate converters
    private static class IsRegisterableConverterFilter implements TypeFilter {
        private static final TypeFilter CONVERTER_TYPE_FILTER = new AssignableTypeFilter(SpecificationConverter.class);
        private static final TypeFilter REGISTERED_ANNOTATION_FILTER = new AnnotationTypeFilter(Registered.class);

        /**
         * {@inheritDoc}
         */
        @Override
        public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
            boolean isConverterType = CONVERTER_TYPE_FILTER.match(metadataReader, metadataReaderFactory);
            boolean isAnnotatedAsRegistered = REGISTERED_ANNOTATION_FILTER.match(metadataReader, metadataReaderFactory);
            return isConverterType && isAnnotatedAsRegistered;
        }
    }

}
