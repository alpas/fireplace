package dev.alpas.fireplace.controllers

import dev.alpas.fireplace.entities.Activities
import dev.alpas.fireplace.entities.Projects
import dev.alpas.fireplace.entities.User
import dev.alpas.fireplace.guards.ProjectMemberInvitationGuard
import dev.alpas.http.HttpCall
import dev.alpas.orAbort
import dev.alpas.routing.Controller
import me.liuwj.ktorm.dsl.insert
import me.liuwj.ktorm.entity.findById

class ProjectMembershipController : Controller() {
    fun add(call: HttpCall) {
        call.validateUsing(ProjectMemberInvitationGuard::class) {
            val member = commit()
            logMembershipActivity(member)
            flash("success", "${member.name} <${member.email}> is now a member of this project.")
            call.redirect().back()
        }
    }

    private fun logMembershipActivity(member: User) {
        val projectId = call.paramAsLong("project").orAbort()
        val project = Projects.findById(projectId).orAbort()
        val now = call.nowInCurrentTimezone().toInstant()
        Activities.insert {
            it.payload to mapOf("action" to "invited", "member" to member.name, "title" to project.title)
            it.projectId to project.id
            it.userId to auth().user?.id
            it.createdAt to now
            it.updatedAt to now
        }
    }
}
