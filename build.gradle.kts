
plugins {
    id("java")
}

group = "uz.lazizkhan"
version = "1.0-SNAPSHOT"

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "Generator"
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.poi:poi-ooxml:5.2.5")
    implementation("org.apache.poi:poi:5.2.5")
    compileOnly("org.projectlombok:lombok:1.18.32")
    annotationProcessor("org.projectlombok:lombok:1.18.32")

}
