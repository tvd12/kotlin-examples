val ezyDataVersion: String by project
val ezyfoxVersion: String by project
val ezyHttpVersion: String by project

dependencies {
    implementation(project(":core"))

    implementation("com.tvd12:ezydata-jpa:$ezyDataVersion")
    implementation("com.tvd12:ezyhttp-server-boot:$ezyHttpVersion")

    implementation("org.hibernate:hibernate-core:5.4.27.Final")
    implementation("mysql:mysql-connector-java:8.0.22")

    testImplementation("com.tvd12:ezyfox-tools:$ezyDataVersion")
}