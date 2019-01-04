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
 * A {@link BuildCustomizer} that automatically adds "reactor-test" when webflux is
 * selected.
 *
 * @author Stephane Nicoll
 * @author Madhura Bhave
 */
public class ReactorTestBuildCustomizer implements BuildCustomizer<Build> {

	private final ResolvedProjectDescription description;

	private static final Version VERSION_2_0_0_M2 = Version.parse("2.0.0.M2");

	public ReactorTestBuildCustomizer(ResolvedProjectDescription description) {
		this.description = description;
	}

	@Override
	public void customize(Build build) {
		if (isSpringBootVersionAtLeastAfter()) {
			build.dependencies().add("reactor-test", "io.projectreactor", "reactor-test",
					null, DependencyType.TEST_COMPILE);
		}
	}

	protected boolean isSpringBootVersionAtLeastAfter() {
		return VERSION_2_0_0_M2.compareTo(this.description.getPlatformVersion()) <= 0;
	}

}
