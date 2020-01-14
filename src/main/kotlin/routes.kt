package dev.alpas.fireplace

import dev.alpas.auth.authRoutes
import dev.alpas.fireplace.controllers.WelcomeController
import dev.alpas.routing.RouteGroup
import dev.alpas.routing.Router

fun Router.addRoutes() = apply {
    group {
        webRoutesGroup()
    }.middlewareGroup("web")
    authRoutes(requireEmailVerification = false)
}

private fun RouteGroup.webRoutesGroup() {
    get("/", WelcomeController::class).name("welcome")
    // register more web routes here
}

