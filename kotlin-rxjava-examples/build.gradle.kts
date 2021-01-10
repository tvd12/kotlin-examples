val ezyHttpVersion: String by project
val quickRpcVersion: String by project
val rxjavaVersion: String by project

dependencies {
    implementation("com.tvd12:ezyhttp-server-boot:$ezyHttpVersion")

    implementation("com.tvd12:quick-rpc-core:$quickRpcVersion")
    implementation("com.tvd12:quick-rpc-server:$quickRpcVersion")
    implementation("com.tvd12:quick-rpc-client:$quickRpcVersion")

    implementation("io.reactivex.rxjava2:rxjava:$rxjavaVersion")
}