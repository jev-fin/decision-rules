plugins {
    java
    id("org.springframework.boot") version "3.0.6"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "org.company"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")

    implementation("org.jeasy:easy-rules-core:4.1.0")
    implementation("org.jeasy:easy-rules-mvel:4.1.0")

    implementation("com.deliveredtechnologies:rulebook-core:0.12")

    implementation("org.evrete:evrete-core:3.0.03")
    implementation("org.evrete:evrete-dsl-java:3.0.03")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
