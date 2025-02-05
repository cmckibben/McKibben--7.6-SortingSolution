plugins {
    id("java")
    application
    `java-library`
}

group = "org.example"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    // https://mvnrepository.com/artifact/commons-io/commons-io
    implementation("commons-io:commons-io:2.17.0")
    // https://mvnrepository.com/artifact/org.apache.commons/commons-lang3
    implementation("org.apache.commons:commons-lang3:3.17.0")
    // https://mvnrepository.com/artifact/com.univocity/univocity-parsers
    implementation("com.univocity:univocity-parsers:2.9.1")


}

tasks.test {
    useJUnitPlatform()
}
