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
import io.spring.initializr.generator.project.build.BuildCustomizer;
import io.spring.initializr.generator.util.Version;

/**
 * {@link BuildCustomizer} for Spring Session that provides explicit handling for the
 * modules introduced in Spring Session 2.
 *
 * @author Stephane Nicoll
 * @author Madhura Bhave
 */
public class SpringSessionBuildCustomizer implements BuildCustomizer<Build> {

	private static final Version VERSION_2_0_0_M3 = Version.parse("2.0.0.M3");

	private final ResolvedProjectDescription description;

	public SpringSessionBuildCustomizer(ResolvedProjectDescription description) {
		this.description = description;
	}

	@Override
	public void customize(Build build) {
		if (isSpringBootVersionAtLeastAfter()) {
			if (hasDependency("data-redis", build)
					|| hasDependency("data-redis-reactive", build)) {
				build.dependencies().add("session-data-redis",
						"org.springframework.session", "spring-session-data-redis",
						DependencyType.COMPILE);
				build.dependencies().filter("session");
			}
			if (hasDependency("jdbc", build)) {
				build.dependencies().add("session-jdbc", "org.springframework.session",
						"spring-session-jdbc", DependencyType.COMPILE);
				build.dependencies().filter("session");
			}
		}
	}

	private boolean isSpringBootVersionAtLeastAfter() {
		return (VERSION_2_0_0_M3.compareTo(this.description.getPlatformVersion()) <= 0);
	}

	private boolean hasDependency(String id, Build build) {
		return build.dependencies().ids().anyMatch(i -> i.equals(id));
	}

}
