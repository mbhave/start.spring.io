/*
 * Copyright 2012-2018 the original author or authors.
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

package io.spring.start.site.buildcustomizers;

import io.spring.initializr.generator.ResolvedProjectDescription;
import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.buildsystem.DependencyType;
import io.spring.initializr.generator.language.kotlin.KotlinLanguage;
import io.spring.initializr.generator.project.build.BuildCustomizer;
import io.spring.initializr.metadata.Dependency;
import io.spring.initializr.metadata.InitializrMetadata;

import org.springframework.util.ClassUtils;

/**
 * A {@link BuildCustomizer} that automatically adds "jackson-module-kotlin" when Kotlin
 * is used and a dependency has the "json" facet.
 *
 * @author Sebastien Deleuze
 * @author Madhura Bhave
 */
public class JacksonKotlinBuildCustomizer implements BuildCustomizer<Build> {

	private final InitializrMetadata metadata;

	private final ResolvedProjectDescription description;

	public JacksonKotlinBuildCustomizer(InitializrMetadata metadata,
			ResolvedProjectDescription description) {
		this.metadata = metadata;
		this.description = description;
	}

	@Override
	public void customize(Build build) {
		boolean isKotlin = ClassUtils.isAssignableValue(KotlinLanguage.class,
				this.description.getLanguage());
		if (hasJsonFacet(build) && isKotlin) {
			build.dependencies().add("jackson-module-kotlin",
					"com.fasterxml.jackson.module", "jackson-module-kotlin",
					DependencyType.COMPILE);
		}
	}

	private boolean hasJsonFacet(Build build) {
		return build.dependencies().ids().anyMatch((id) -> {
			Dependency dependency = this.metadata.getDependencies().get(id);
			if (dependency != null) {
				return dependency.getFacets().contains("json");
			}
			return false;
		});
	}

}
