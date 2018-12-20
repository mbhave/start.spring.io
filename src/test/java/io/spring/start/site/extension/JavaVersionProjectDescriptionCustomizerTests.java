/*
 * Copyright (c) 2018. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package io.spring.start.site.extension;

import io.spring.initializr.generator.ProjectRequest;
import io.spring.start.site.buildcustomizers.AbstractCustomizerTests;
import org.junit.Test;

/**
 * Tests for {@link JavaVersionRequestPostProcessor}.
 *
 * @author Stephane Nicoll
 */
public class JavaVersionProjectDescriptionCustomizerTests
		extends AbstractCustomizerTests {

	@Test
	public void java9CannotBeUsedWithSpringBoot1Maven() {
		ProjectRequest request = createProjectRequest("web");
		request.setBootVersion("1.5.8.RELEASE");
		request.setJavaVersion("9");
		generateMavenPom(request).hasJavaVersion("1.8");
	}

	@Test
	public void java9CannotBeUsedWithSpringBoot1Gradle() {
		ProjectRequest request = createProjectRequest("data-jpa");
		request.setBootVersion("1.99.99.BUILD-SNAPSHOT");
		request.setJavaVersion("9");
		generateGradleBuild(request).hasJavaVersion("1.8");
	}

	@Test
	public void java9CannotBeUsedWithGroovyMaven() {
		ProjectRequest request = createProjectRequest("web");
		request.setBootVersion("2.0.1.RELEASE");
		request.setLanguage("groovy");
		request.setJavaVersion("9");
		generateMavenPom(request).hasJavaVersion("1.8");
	}

	@Test
	public void java9CannotBeUsedWithKotlinMaven() {
		ProjectRequest request = createProjectRequest("web");
		request.setBootVersion("2.0.1.RELEASE");
		request.setLanguage("kotlin");
		request.setJavaVersion("9");
		generateMavenPom(request).hasJavaVersion("1.8");
	}

	@Test
	public void java9CannotBeUsedWithGroovyGradle() {
		ProjectRequest request = createProjectRequest("data-jpa");
		request.setBootVersion("2.0.1.RELEASE");
		request.setLanguage("groovy");
		request.setJavaVersion("9");
		generateGradleBuild(request).hasJavaVersion("1.8");
	}

	@Test
	public void java9CannotBeUsedWithKotlinGradle() {
		ProjectRequest request = createProjectRequest("data-jpa");
		request.setBootVersion("2.0.1.RELEASE");
		request.setLanguage("kotlin");
		request.setJavaVersion("9");
		generateGradleBuild(request).hasJavaVersion("1.8");
	}

	@Test
	public void java9CanBeUsedWithSpringBoot2Maven() {
		ProjectRequest request = createProjectRequest("web");
		request.setBootVersion("2.0.1.RELEASE");
		request.setJavaVersion("9");
		generateMavenPom(request).hasJavaVersion("9");
	}

	@Test
	public void java9CanBeUsedWithSpringBoot2Gradle() {
		ProjectRequest request = createProjectRequest("data-jpa");
		request.setBootVersion("2.0.0.M3");
		request.setJavaVersion("9");
		generateGradleBuild(request).hasJavaVersion("9");
	}

	@Test
	public void java10CannotBeUsedWithSpringBoot1Maven() {
		ProjectRequest request = createProjectRequest("web");
		request.setBootVersion("1.5.8.RELEASE");
		request.setJavaVersion("10");
		generateMavenPom(request).hasJavaVersion("1.8");
	}

	@Test
	public void java10CannotBeUsedWithSpringBoot1Gradle() {
		ProjectRequest request = createProjectRequest("data-jpa");
		request.setBootVersion("1.99.99.BUILD-SNAPSHOT");
		request.setJavaVersion("10");
		generateGradleBuild(request).hasJavaVersion("1.8");
	}

	@Test
	public void java10CannotBeUsedWithSpringBoot200Maven() {
		ProjectRequest request = createProjectRequest("web");
		request.setBootVersion("2.0.0.RELEASE");
		request.setJavaVersion("10");
		generateMavenPom(request).hasJavaVersion("1.8");
	}

	@Test
	public void java10CannotBeUsedWithSpringBoot200Gradle() {
		ProjectRequest request = createProjectRequest("data-jpa");
		request.setBootVersion("2.0.0.RELEASE");
		request.setJavaVersion("10");
		generateGradleBuild(request).hasJavaVersion("1.8");
	}

	@Test
	public void java10CanBeUsedWithSpringBoot2Maven() {
		ProjectRequest request = createProjectRequest("web");
		request.setBootVersion("2.0.1.RELEASE");
		request.setJavaVersion("10");
		generateMavenPom(request).hasJavaVersion("10");
	}

	@Test
	public void java10CanBeUsedWithSpringBoot2Gradle() {
		ProjectRequest request = createProjectRequest("data-jpa");
		request.setBootVersion("2.0.2.RELEASE");
		request.setJavaVersion("10");
		generateGradleBuild(request).hasJavaVersion("10");
	}

	@Test
	public void java11CannotBeUsedWithSpringBoot1Maven() {
		ProjectRequest request = createProjectRequest("web");
		request.setBootVersion("1.5.8.RELEASE");
		request.setJavaVersion("11");
		generateMavenPom(request).hasJavaVersion("1.8");
	}

	@Test
	public void java11CannotBeUsedWithSpringBoot1Gradle() {
		ProjectRequest request = createProjectRequest("data-jpa");
		request.setBootVersion("1.99.99.BUILD-SNAPSHOT");
		request.setJavaVersion("11");
		generateGradleBuild(request).hasJavaVersion("1.8");
	}

	@Test
	public void java11CannotBeUsedWithSpringBoot20Maven() {
		ProjectRequest request = createProjectRequest("web");
		request.setBootVersion("2.0.5.RELEASE");
		request.setJavaVersion("11");
		generateMavenPom(request).hasJavaVersion("1.8");
	}

	@Test
	public void java11CannotBeUsedWithSpringBoot20Gradle() {
		ProjectRequest request = createProjectRequest("data-jpa");
		request.setBootVersion("2.0.5.RELEASE");
		request.setJavaVersion("11");
		generateGradleBuild(request).hasJavaVersion("1.8");
	}

	@Test
	public void java11CanBeUsedWithSpringBoot21Maven() {
		ProjectRequest request = createProjectRequest("web");
		request.setBootVersion("2.1.0.M1");
		request.setJavaVersion("11");
		generateMavenPom(request).hasJavaVersion("11");
	}

	@Test
	public void java11CanBeUsedWithSpringBoot21Gradle() {
		ProjectRequest request = createProjectRequest("data-jpa");
		request.setBootVersion("2.1.1.RELEASE");
		request.setJavaVersion("11");
		generateGradleBuild(request).hasJavaVersion("11");
	}

}
