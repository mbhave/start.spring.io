/*
 * Copyright 2012-2019 the original author or authors.
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

package io.spring.start.site.extension;

import io.spring.initializr.generator.ResolvedProjectDescription;
import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.buildsystem.DependencyType;
import io.spring.initializr.generator.project.build.BuildCustomizer;
import io.spring.initializr.generator.util.Version;

/**
 * Determine the appropriate Spring Cloud function dependency according to the messaging
 * and/or platform dependencies requested.
 *
 * @author Dave Syer
 * @author Stephane Nicoll
 * @author Madhura Bhave
 */
class SpringCloudFunctionBuildCustomizer implements BuildCustomizer<Build> {

	private static final Version VERSION_2_1_0_M1 = Version.parse("2.1.0.M1");

	private final ResolvedProjectDescription description;

	SpringCloudFunctionBuildCustomizer(ResolvedProjectDescription description) {
		this.description = description;
	}

	@Override
	public void customize(Build build) {
		if (hasDependency("cloud-function", build)) {
			if ((hasDependency("cloud-stream", build)
					|| hasDependency("reactive-cloud-stream", build))
					&& isSpringBootVersionBefore()) {
				build.dependencies().add("cloud-function-stream",
						"org.springframework.cloud", "spring-cloud-function-stream",
						DependencyType.COMPILE);
				build.dependencies().filter("cloud-function");
			}
			if (hasDependency("web", build)) {
				build.dependencies().add("cloud-function-web",
						"org.springframework.cloud", "spring-cloud-function-web",
						DependencyType.COMPILE);
				build.dependencies().filter("cloud-function");
			}
			if (hasDependency("webflux", build) && isSpringBootVersionAtLeastAfter()) {
				build.dependencies().add("cloud-function-web",
						"org.springframework.cloud", "spring-cloud-function-web",
						DependencyType.COMPILE);
				build.dependencies().filter("cloud-function");
			}
		}
	}

	private boolean hasDependency(String id, Build build) {
		return build.dependencies().ids().anyMatch(i -> i.equals(id));
	}

	private boolean isSpringBootVersionAtLeastAfter() {
		return (VERSION_2_1_0_M1.compareTo(this.description.getPlatformVersion()) <= 0);
	}

	private boolean isSpringBootVersionBefore() {
		return (VERSION_2_1_0_M1.compareTo(this.description.getPlatformVersion()) > 0);
	}

}
