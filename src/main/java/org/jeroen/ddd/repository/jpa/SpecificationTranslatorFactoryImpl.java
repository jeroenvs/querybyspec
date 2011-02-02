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
 * Default implementation of {@link SpecificationTranslatorFactory}.
 * 
 * @author Jeroen van Schagen
 * @since 30-12-2010
 */
public class SpecificationTranslatorFactoryImpl implements SpecificationTranslatorFactory, ApplicationContextAware {
    private ApplicationContext applicationContext;

    /**
     * {@inheritDoc}
     */
    @Override
    public SpecificationTranslator createWithDefaultConverters() {
        SpecificationTranslator translator = new SpecificationTranslatorImpl();
        translator.registerConverter(new EqualityConverter());
        translator.registerConverter(new NotConverter(translator));
        translator.registerConverter(new AndConverter(translator));
        translator.registerConverter(new OrConverter(translator));
        return translator;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SpecificationTranslator createWithAnnotatedConverters(String basePackage) {
        SpecificationTranslator translator = createWithDefaultConverters();
        // Register any annotated converter in the provided base package
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
        scanner.addIncludeFilter(new IsRegisterableConverterFilter());
        for (BeanDefinition bd : scanner.findCandidateComponents(basePackage)) {
            try {
                Class<?> converterClass = Class.forName(bd.getBeanClassName());
                translator.registerConverter(getConverterOfType(converterClass));
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
    private SpecificationConverter<?, ?> getConverterOfType(Class<?> converterClass) {
        Object instance = null;
        try {
            instance = applicationContext.getBean(converterClass);
        } catch (NoSuchBeanDefinitionException e) {
            instance = BeanUtils.instantiate(converterClass);
        }
        return (SpecificationConverter<?, ?>) instance;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
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
