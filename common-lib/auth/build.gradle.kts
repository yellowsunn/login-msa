plugins {
    java
}

val jacksonVersion = "2.14.2"
val jjwtVersion = "0.11.5"
val assertjVersion = "3.23.1"
val junitJupiterVersion = "5.9.2"

group "com.yellowsunn.common"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.fasterxml.jackson.core:jackson-databind:$jacksonVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonVersion")
    implementation("io.jsonwebtoken:jjwt-api:$jjwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:$jjwtVersion")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:$jjwtVersion")
    testImplementation("org.assertj:assertj-core:$assertjVersion")
    testImplementation("org.junit.jupiter:junit-jupiter:$junitJupiterVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
