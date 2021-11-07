repositories {
    mavenCentral()
}

tasks.bootJar{
    enabled = true
}

//tasks.jar{
//    enabled = true
//}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":bidding-common"))
    implementation(project(":bidding-model"))
}