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

import io.spring.initializr.generator.buildsystem.Build;
import io.spring.initializr.generator.buildsystem.DependencyType;
import io.spring.initializr.generator.project.build.BuildCustomizer;

/**
 * Determine the appropriate Spring Cloud stream dependency to use based on the selected
 * integration technology.
 * <p>
 * Does not replace the integration technology jar by the relevant binder. If more than
 * one tech is selected, it is far more easier to remove the unnecessary binder jar than
 * to figure out the name of the tech jar to add to keep support for that technology.
 *
 * @author Stephane Nicoll
 * @author Madhura Bhave
 */
class SpringCloudStreamBuildCustomizer implements BuildCustomizer<Build> {

	@Override
	public void customize(Build build) {
		if (hasDependency("cloud-stream", build)
				|| hasDependency("reactive-cloud-stream", build)
				|| hasDependency("cloud-bus", build)
				|| hasDependency("cloud-turbine-stream", build)) {
			if (hasDependency("amqp", build)) {
				build.dependencies().add("cloud-stream-binder-rabbit",
						"org.springframework.cloud", "spring-cloud-stream-binder-rabbit",
						DependencyType.COMPILE);
			}
			if (hasDependency("kafka", build)) {
				build.dependencies().add("cloud-stream-binder-kafka",
						"org.springframework.cloud", "spring-cloud-stream-binder-kafka",
						DependencyType.COMPILE);
			}
		}
		// Spring Cloud Stream specific
		if (hasDependency("cloud-stream", build)
				|| hasDependency("reactive-cloud-stream", build)) {
			if (hasDependency("kafka-streams", build)) {
				build.dependencies().add("cloud-stream-binder-kafka-streams",
						"org.springframework.cloud",
						"spring-cloud-stream-binder-kafka-streams",
						DependencyType.COMPILE);
			}
			build.dependencies().add("cloud-stream-test", "org.springframework.cloud",
					"spring-cloud-stream-test-support", null,
					DependencyType.TEST_COMPILE);
		}
	}

	protected boolean hasDependency(String id, Build build) {
		return build.dependencies().ids().anyMatch(i -> i.equals(id));
	}

}
