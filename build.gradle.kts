plugins {
    java
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}



dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.theokanning.openai-gpt3-java:api:0.12.0")
    implementation("com.theokanning.openai-gpt3-java:service:0.12.0")
    implementation("com.theokanning.openai-gpt3-java:client:0.12.0")
    implementation("org.thymeleaf:thymeleaf:3.1.1.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("com.google.code.gson:gson:2.7")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<JavaExec> {
    systemProperty("console.encoding", "UTF-8")
}
