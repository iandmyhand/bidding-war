plugins {
	id("org.springframework.boot") version "2.5.4"
	// id("io.spring.dependency-management") version "1.0.11.RELEASE"

	kotlin("jvm") version "1.5.21"
	kotlin("plugin.spring") version "1.5.21"
	kotlin("plugin.jpa") version "1.5.21"
	// kotlin("plugin.serialization") version "1.5.21"

	id("org.jetbrains.kotlin.plugin.allopen") version "1.4.10"
	id("org.jetbrains.kotlin.plugin.noarg") version "1.4.10"
}

allprojects {
	group = "com.study"
	version = "0.0.1-SNAPSHOT"

	repositories {
		mavenCentral()
	}
}

subprojects {
	apply(plugin = "org.jetbrains.kotlin.plugin.allopen")
	apply(plugin = "org.jetbrains.kotlin.plugin.noarg")

	configurations {
		all {
			exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
		}
	}

	allOpen {
		annotation("javax.persistence.MappedSuperclass")
		annotation("javax.persistence.Embeddable")
	}

	noArg {
		annotation("javax.persistence.Entity")
	}
}