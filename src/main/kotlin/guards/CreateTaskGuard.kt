package dev.alpas.fireplace.guards

import dev.alpas.fireplace.entities.Task
import dev.alpas.fireplace.entities.Tasks
import dev.alpas.ozone.create
import dev.alpas.validation.JsonField
import dev.alpas.validation.Rule
import dev.alpas.validation.ValidationGuard
import dev.alpas.validation.required

open class CreateTaskGuard : ValidationGuard() {
    override fun rules(): Map<String, Iterable<Rule>> {
        return mapOf("body" to listOf(JsonField(required())))
    }

    open fun commit(): Task {
        val now = call.nowInCurrentTimezone().toInstant()
        return Tasks.create {
            it.body to call.jsonBody?.get("body")
            it.projectId to call.paramAsLong("project")
            it.createdAt to now
            it.updatedAt to now
        }
    }
}
