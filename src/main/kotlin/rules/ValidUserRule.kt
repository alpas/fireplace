package dev.alpas.fireplace.rules

import dev.alpas.fireplace.entities.ProjectMemberships
import dev.alpas.fireplace.entities.User
import dev.alpas.fireplace.entities.Users
import dev.alpas.http.HttpCall
import dev.alpas.orAbort
import dev.alpas.validation.Rule
import me.liuwj.ktorm.dsl.and
import me.liuwj.ktorm.dsl.eq
import me.liuwj.ktorm.entity.findOne

class ValidUserRule : Rule() {
    var user: User? = null
        private set

    override fun check(attribute: String, call: HttpCall): Boolean {
        val email = call.paramAsString("email").orAbort()

        // A project owner cannot invite themselves
        if (email == call.caller<User>().email) {
            error = "You are the owner of this project."
            return false
        }

        // The user should already be registered
        val user = Users.findOne { it.email eq email }
        if (user == null) {
            error = "The user must already be registered with ${call.env("APP_NAME")}."
            return false
        }

        // The user shouldn't already be a member of this project
        val projectId = call.paramAsLong("project").orAbort()
        val membership = ProjectMemberships.findOne {
            (it.projectId eq projectId) and (it.userId eq user.id)
        }
        if (membership != null) {
            error = "The user is already a member of this project."
            return false
        }

        this.user = user
        return true
    }
}
