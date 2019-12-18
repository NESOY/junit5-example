## JUnit5 Tutorial

## Requirements
- Java8

## [Architecture](https://github.com/junit-team/junit5-workshop/blob/master/slides/05_modularization/modularization.md)
- Platform
    - 테스트를 실행해주는 런처 제공. TestEngine API 제공.
    - Maven / Gradle Plugins, JUnit4 Runner
- Jupiter
    - JUnit 5를 지원하는 TestEngine API 구현체
- Vintage 
    - JUnit 4와 3을 지원하는 TestEngine API 구현체

## [Index](https://github.com/NESOY/junit5-example/tree/master/src/test/java)
- [Standard](https://github.com/NESOY/junit5-example/tree/master/src/test/java/standard)
- [Assertions](https://github.com/NESOY/junit5-example/tree/master/src/test/java/assertions)
- [assumptions](https://github.com/NESOY/junit5-example/tree/master/src/test/java/assumptions)
- [meta](https://github.com/NESOY/junit5-example/tree/master/src/test/java/meta)
- [disabled](https://github.com/NESOY/junit5-example/tree/master/src/test/java/disabled)
- [displayname](https://github.com/NESOY/junit5-example/tree/master/src/test/java/displayname)
- [timeout](https://github.com/NESOY/junit5-example/tree/master/src/test/java/timeout)
- [order](https://github.com/NESOY/junit5-example/tree/master/src/test/java/order)
- [repeat](https://github.com/NESOY/junit5-example/tree/master/src/test/java/repeat)
- [test_info](https://github.com/NESOY/junit5-example/tree/master/src/test/java/test_info)
- [test_interface](https://github.com/NESOY/junit5-example/tree/master/src/test/java/test_interface)
- [parameterized](https://github.com/NESOY/junit5-example/tree/master/src/test/java/parameterized)
- [conditional](https://github.com/NESOY/junit5-example/tree/master/src/test/java/conditional)
- [extension_model](https://github.com/NESOY/junit5-example/tree/master/src/test/java/extension_model)
- [dynamic_test](https://github.com/NESOY/junit5-example/tree/master/src/test/java/dynamic_test)
- [parallel](https://github.com/NESOY/junit5-example/tree/master/src/test/java/parallel)

## JUnit4 Migration Guide
> JUnit 4 & JUnit 5 동시에 할 수 없을까?
- [Migration Sample](https://github.com/junit-team/junit5-samples#migration-samples)

#### [Maven](https://github.com/junit-team/junit5-samples/blob/master/junit5-migration-maven)
```xml
<dependencies>
        <!--  JUnit5  -->
        <dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter-api</artifactId>
			<version>${junit.jupiter.version}</version>
			<scope>test</scope>
		</dependency>
        <dependency>
			<groupId>org.junit.jupiter</groupId>
			<artifactId>junit-jupiter-engine</artifactId>
			<version>${junit.jupiter.version}</version>
			<scope>test</scope>
		</dependency>
        <!--  JUnit4  -->
		<dependency>
			<groupId>junit</groupId>
			<artifactId>junit</artifactId>
			<version>${junit.version}</version>
			<scope>test</scope>
		</dependency>
        <dependency>
			<groupId>org.junit.vintage</groupId>
			<artifactId>junit-vintage-engine</artifactId>
			<version>${junit.vintage.version}</version>
			<scope>test</scope>
		</dependency>
</dependencies>
```

#### [Gradle](https://github.com/junit-team/junit5-samples/tree/master/junit5-migration-gradle)
```gradle
dependencies {
    // JUnit 5
    testImplementation('org.junit.jupiter:junit-jupiter:5.5.2')
    testRuntimeOnly 'org.junit.jupiter:junit-jupiter-engine:5.5.2'

    // JUnit 4
    testImplementation("junit:junit:4.12")
    testRuntimeOnly "org.junit.vintage:junit-vintage-engine:5.5.2"
}
```

## JUnit5 Testing in IDE 
- [Intellij](https://blog.jetbrains.com/idea/2016/08/using-junit-5-in-intellij-idea/)
- [vscode](https://code.visualstudio.com/docs/java/java-testing)

### Reference
- <https://junit.org/junit5/docs/current/user-guide/>
- [assertThat Dependency 없음(Hamcrest, AssertJ)](https://github.com/junit-team/junit5/issues/147)
- [Support JUnit 5 in SpringBoot Version 2.2](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-2.2-Release-Notes#junit-5)
- [JUnit5 in Spring - Eddy Kim님](https://brunch.co.kr/@springboot/77)