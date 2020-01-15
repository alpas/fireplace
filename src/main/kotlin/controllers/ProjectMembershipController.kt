package dev.alpas.fireplace.controllers

import dev.alpas.fireplace.guards.ProjectMemberInvitationGuard
import dev.alpas.http.HttpCall
import dev.alpas.routing.Controller

class ProjectMembershipController : Controller() {
    fun add(call: HttpCall) {
        call.validateUsing(ProjectMemberInvitationGuard::class) {
            val member = commit()
            flash("success", "${member.name} <${member.email}> is now a member of this project.")
            call.redirect().back()
        }
    }
}
