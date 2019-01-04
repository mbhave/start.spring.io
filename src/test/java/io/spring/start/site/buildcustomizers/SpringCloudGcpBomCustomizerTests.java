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
import org.junit.Test;

/**
 * Tests for {@link SpringCloudGcpBomBuildCustomizer}.
 *
 * @author Stephane Nicoll
 */
public class SpringCloudGcpBomCustomizerTests extends AbstractCustomizerTests {

	@Test
	public void springCloudGcpOffReleaseTrain() {
		ProjectRequest request = createProjectRequest("cloud-gcp");
		request.setBootVersion("2.0.6.RELEASE");
		generateMavenPom(request)
				.hasBom("org.springframework.cloud", "spring-cloud-gcp-dependencies",
						"1.0.0.RELEASE")
				.hasBom("org.springframework.cloud", "spring-cloud-dependencies",
						"${spring-cloud.version}")
				.hasBomsCount(2);
	}

	@Test
	public void springCloudGcpOnReleaseTrain() {
		ProjectRequest request = createProjectRequest("cloud-gcp");
		request.setBootVersion("2.1.1.RELEASE");
		generateMavenPom(request).hasBom("org.springframework.cloud",
				"spring-cloud-dependencies", "${spring-cloud.version}").hasBomsCount(1);
	}

}
