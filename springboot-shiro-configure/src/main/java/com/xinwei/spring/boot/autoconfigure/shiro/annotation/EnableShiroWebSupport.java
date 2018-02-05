package com.xinwei.spring.boot.autoconfigure.shiro.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import com.xinwei.spring.boot.autoconfigure.shiro.ShiroWebMvcConfigurerAdapter;


/**
 * Annotation to automatically register the following beans for usage with Spring MVC.
 * <ul>
 * <li>
 * {@link com.xinwei.spring.boot.autoconfigure.shiro.annotation.SessionUser}.
 * </li>
 * </ul>
 * @author John Zhang
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Inherited
@Import({ EnableShiroWebSupport.ShiroWebMvcConfigurerAdapterImportSelector.class })
public @interface EnableShiroWebSupport {

    static class ShiroWebMvcConfigurerAdapterImportSelector implements ImportSelector {

        @Override
        public String[] selectImports(AnnotationMetadata importingClassMetadata) {
            return new String[] { ShiroWebMvcConfigurerAdapter.class.getName() };
        }

    }
}
