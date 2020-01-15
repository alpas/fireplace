package dev.alpas.fireplace.guards

import dev.alpas.fireplace.entities.Task
import dev.alpas.fireplace.entities.Tasks
import dev.alpas.orAbort
import dev.alpas.ozone.findOrFail
import me.liuwj.ktorm.dsl.eq
import me.liuwj.ktorm.dsl.update

class UpdateTaskGuard : CreateTaskGuard() {
    override fun commit(): Task {
        val id = call.paramAsLong("id").orAbort()

        Tasks.update {
            it.body to call.jsonBody?.get("body")
            val isCompleted = call.jsonBody?.get("completed")
            if (isCompleted != null) {
                it.completed to isCompleted
            }
            where { it.id eq id }
        }
        return Tasks.findOrFail(id)
    }
}
