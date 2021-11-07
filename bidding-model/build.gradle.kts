repositories {
    mavenCentral()
}

tasks.jar{
    enabled = true
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":bidding-common"))
}

