val ezyDataVersion: String by project
val ezyfoxVersion: String by project
val ezyHttpVersion: String by project

dependencies {
    implementation(project(":core"))

    implementation("com.tvd12:ezydata-redis:$ezyDataVersion")
    implementation("com.tvd12:ezyhttp-server-boot:$ezyHttpVersion")

    testImplementation("com.tvd12:ezyfox-tools:$ezyDataVersion")
}