import com.google.protobuf.gradle.id

plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
	id("com.google.protobuf") version "0.9.4"
}

group = "ru.liljarn"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
	sourceCompatibility = JavaVersion.VERSION_21
	targetCompatibility = JavaVersion.VERSION_21
}

repositories {
	mavenCentral()
}

protobuf {
	protoc {
		artifact = "com.google.protobuf:protoc:4.28.2"
	}

	plugins {
		id("grpc") {
			artifact = "io.grpc:protoc-gen-grpc-java:1.68.0"
		}
		create("grpckt") {
			artifact = "io.grpc:protoc-gen-grpc-kotlin:1.4.1:jdk8@jar"
		}
	}

	generateProtoTasks {
		all().forEach {
			it.plugins {
				id("grpc") { }
				id("grpckt") { }
			}
			it.builtins {
				create("kotlin")
			}
		}
	}
}

sourceSets {
	main {
		java {
			srcDir("src/main/proto")
		}
	}
}

dependencies {
	// WEB
	implementation("org.springframework.boot:spring-boot-starter-web")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-configuration-processor")

	// JACKSON
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	// DATABASE
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	runtimeOnly("org.postgresql:postgresql")
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-database-postgresql")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// TEST
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// SECURITY
	implementation("org.mindrot:jbcrypt:0.4")
	implementation("io.jsonwebtoken:jjwt-api:0.12.6")
	implementation("io.jsonwebtoken:jjwt-impl:0.12.6")
	implementation("io.jsonwebtoken:jjwt-jackson:0.12.6")

	// LOGGING
	implementation("io.github.oshai:kotlin-logging-jvm:7.0.0")

	// gRPC
	implementation("net.devh:grpc-server-spring-boot-starter:3.1.0.RELEASE")
	implementation(platform("io.grpc:grpc-bom:1.68.0"))
	implementation("io.grpc:grpc-api")
	implementation("io.grpc:grpc-core")
	implementation("io.grpc:grpc-protobuf")
	implementation("io.grpc:grpc-kotlin-stub:1.4.1")
	implementation("io.grpc:grpc-stub")
	implementation("io.grpc:grpc-netty-shaded")
	implementation("com.google.protobuf:protobuf-java-util:4.28.2")
	implementation("com.google.protobuf:protobuf-kotlin:4.28.2")

	// S3
	implementation("io.minio:minio:8.5.13")

	// KAFKA
	implementation("org.springframework.kafka:spring-kafka")
	testImplementation("org.springframework.kafka:spring-kafka-test")

	// REFLECTION
	implementation("org.springframework.boot:spring-boot-starter-aop")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
