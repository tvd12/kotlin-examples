val quickRpcVersion: String by project

dependencies {
    implementation("com.tvd12:quick-rpc-core:$quickRpcVersion")
    implementation("com.tvd12:quick-rpc-server:$quickRpcVersion")
    implementation("com.tvd12:quick-rpc-client:$quickRpcVersion")
}