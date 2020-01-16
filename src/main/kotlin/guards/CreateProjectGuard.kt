package dev.alpas.fireplace.guards

import dev.alpas.fireplace.entities.Project
import dev.alpas.fireplace.entities.Projects
import dev.alpas.ozone.create
import dev.alpas.validation.Rule
import dev.alpas.validation.ValidationGuard
import dev.alpas.validation.min
import dev.alpas.validation.required

class CreateProjectGuard : ValidationGuard() {
    override fun rules(): Map<String, Iterable<Rule>> {
        return mapOf("title" to listOf(required(), min(8)), "description" to listOf(required()))
    }

    fun commit(): Project {
        val now = call.nowInCurrentTimezone().toInstant()
        return Projects.create {
            it.title to call.param("title")
            it.description to call.param("description")
            it.ownerId to call.user.id
            it.createdAt to now
            it.updatedAt to now
        }
    }
}
