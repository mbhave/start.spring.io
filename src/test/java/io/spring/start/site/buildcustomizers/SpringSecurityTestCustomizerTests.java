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

import io.spring.initializr.generator.ProjectRequest;
import io.spring.initializr.metadata.Dependency;
import org.junit.Test;

/**
 * Tests for {@link SpringSecurityTestBuildCustomizer}.
 *
 * @author Stephane Nicoll
 */
public class SpringSecurityTestCustomizerTests extends AbstractCustomizerTests {

	@Test
	public void securityTestIsAddedWithSecurity() {
		ProjectRequest request = createProjectRequest("security");
		generateMavenPom(request).hasSpringBootStarterDependency("security")
				.hasSpringBootStarterTest().hasDependency(springSecurityTest())
				.hasDependenciesCount(3);
	}

	@Test
	public void securityTestIsNotAddedWithoutSpringSecurity() {
		ProjectRequest request = createProjectRequest("web");
		generateMavenPom(request).hasSpringBootStarterDependency("web")
				.hasSpringBootStarterTest().hasDependenciesCount(2);
	}

	private static Dependency springSecurityTest() {
		Dependency dependency = Dependency.withId("spring-security-test",
				"org.springframework.security", "spring-security-test");
		dependency.setScope(Dependency.SCOPE_TEST);
		return dependency;
	}

}
