/*
 * Copyright 2012-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.test.autoconfigure.json;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.OverrideAutoConfiguration;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.autoconfigure.filter.TypeExcludeFilters;
import org.springframework.boot.test.context.SpringBootTestContextBootstrapper;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.test.context.BootstrapWith;

/**
 * Annotation that can be used in combination with {@code @RunWith(SpringRunner.class)}
 * for a typical JSON test. Can be used when a test focuses <strong>only</strong> on JSON
 * serialization.
 * <p>
 * Using this annotation will disable full auto-configuration and instead apply only
 * configuration relevant to Json tests (i.e. {@code @JsonComponent}, Jackson
 * {@code Module})
 * <p>
 * By default, tests annotated with {@code JsonTest} will also initialize
 * {@link JacksonTester} and {@link GsonTester} fields. More fine-grained control can be
 * provided via the {@link AutoConfigureJsonTesters @AutoConfigureJsonTesters} annotation.
 *
 * @author Phillip Webb
 * @see AutoConfigurationJson
 * @see AutoConfigureJsonTesters
 * @see AutoConfigureCache
 * @since 1.4.0
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@BootstrapWith(SpringBootTestContextBootstrapper.class)
@OverrideAutoConfiguration(enabled = false)
@TypeExcludeFilters(JsonExcludeFilter.class)
@AutoConfigureCache
@AutoConfigurationJson
@AutoConfigureJsonTesters
public @interface JsonTest {

	/**
	 * Determines if default filtering should be used with
	 * {@link SpringBootApplication @SpringBootApplication}. By default only
	 * {@code @JsonComponent} and {@code Module} beans are included.
	 * @see #includeFilters()
	 * @see #excludeFilters()
	 * @return if default filters should be used
	 */
	boolean useDefaultFilters() default true;

	/**
	 * A set of include filters which can be used to add otherwise filtered beans to the
	 * application context.
	 * @return include filters to apply
	 */
	Filter[] includeFilters() default {};

	/**
	 * A set of exclude filters which can be used to filter beans that would otherwise be
	 * added to the application context.
	 * @return exclude filters to apply
	 */
	Filter[] excludeFilters() default {};

}
