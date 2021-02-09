rootProject.name = "kotlin-examples"

include("core")
include("bean")
include("data-binding")
include("jpa")
include("mongo")
include("properties-file")
include("rpc")
include("rxjava")

for (project in rootProject.children) {
    project.projectDir = file("kotlin-${project.name}-examples")
}