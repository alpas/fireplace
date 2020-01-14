package dev.alpas.fireplace.controllers

import dev.alpas.fireplace.entities.Projects
import dev.alpas.fireplace.entities.User
import dev.alpas.fireplace.guards.CreateProjectGuard
import dev.alpas.http.HttpCall
import dev.alpas.orAbort
import dev.alpas.ozone.create
import dev.alpas.routing.Controller
import me.liuwj.ktorm.dsl.delete
import me.liuwj.ktorm.dsl.eq
import me.liuwj.ktorm.entity.findAll

class ProjectController : Controller() {
    fun index(call: HttpCall) {
        val caller = caller<User>()
        val projects = caller.projects
        call.render("project_list", mapOf("projects" to projects))
    }

    fun create(call: HttpCall) {
        call.render("project_new")
    }

    fun store(call: HttpCall) {
        call.validateUsing(CreateProjectGuard::class) {
            val project = commit()
            flash("success", "Successfully added project '${project.title}'!")
        }
        call.redirect().toRouteNamed("projects.list")
    }

    fun delete(call: HttpCall) {
        val id = call.paramAsLong("id").orAbort()
        Projects.delete { it.id eq id }
        flash("success", "Successfully deleted the project!")
        call.redirect().back()
    }
}
