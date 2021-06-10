plugins {
    id("org.springframework.boot")
    kotlin("jvm")
    kotlin("plugin.spring")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    runtimeOnly("com.h2database:h2")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-web")

    implementation(project(":tacocloud-data"))
    implementation(project(":tacocloud-api"))
    implementation(project(":tacocloud-security"))
    implementation(project(":tacocloud-domain"))
    implementation(project(":tacocloud-ui"))
    implementation(project(":tacocloud-web"))
}