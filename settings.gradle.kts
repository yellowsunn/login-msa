rootProject.name = "login-msa"

include(
    "common-lib:data",
    "common-lib:auth",

    "auth-service",
    "apigateway-service",
    "user-service",
)
