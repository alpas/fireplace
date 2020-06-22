package dev.alpas.fireplace

import dev.alpas.Environment
import dev.alpas.auth.authRoutes
import dev.alpas.fireplace.controllers.ProjectController
import dev.alpas.fireplace.controllers.ProjectMembershipController
import dev.alpas.fireplace.controllers.TaskController
import dev.alpas.fireplace.controllers.WelcomeController
import dev.alpas.routing.RouteGroup
import dev.alpas.routing.Router

fun Router.addRoutes(env: Environment) = apply {
    group {
        webRoutesGroup()
    }.middlewareGroup("web")
    authRoutes(requireEmailVerification = env.isDev, allowPasswordReset = env.isDev, addHomeRoute = false)
}

private fun RouteGroup.webRoutesGroup() {
    get("/home") {
        redirect().toRouteNamed("projects.list")
    }

    get("/", WelcomeController::class).name("welcome")

    group("projects") {
        addProjectRoutes()
        addTaskRoutes()
        addMembershipRoutes()
    }.name("projects").mustBeAuthenticated()
}

private fun RouteGroup.addProjectRoutes() {
    get("/", ProjectController::class).name("list")
    get("/create", ProjectController::class, "create").name("create")
    get("/<id>", ProjectController::class, "show").name("show")
    post("/", ProjectController::class).name("store")
    delete("/", ProjectController::class).name("delete")
}

private fun RouteGroup.addTaskRoutes() {
    group("<project>/tasks") {
        post(TaskController::class)
        delete("/<id>", TaskController::class)
        patch("/<id>", TaskController::class)
    }.name("tasks")
}

private fun RouteGroup.addMembershipRoutes() {
    group("<project>/membership") {
        post(ProjectMembershipController::class, "add").name("add")
    }.name("membership")
}
