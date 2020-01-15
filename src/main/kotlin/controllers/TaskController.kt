package dev.alpas.fireplace.controllers

import dev.alpas.fireplace.entities.Activities
import dev.alpas.fireplace.entities.Task
import dev.alpas.fireplace.entities.Tasks
import dev.alpas.fireplace.entities.User
import dev.alpas.fireplace.guards.CreateTaskGuard
import dev.alpas.fireplace.guards.UpdateTaskGuard
import dev.alpas.http.HttpCall
import dev.alpas.orAbort
import dev.alpas.routing.Controller
import me.liuwj.ktorm.dsl.delete
import me.liuwj.ktorm.dsl.eq
import me.liuwj.ktorm.dsl.insert

class TaskController : Controller() {
    fun store(call: HttpCall) {
        call.validateUsing(CreateTaskGuard::class) {
            val task = commit()
            logTaskActivity(task, mapOf("action" to "created task", "title" to task.body))
            call.replyAsJson(task)
        }
    }

    fun delete(call: HttpCall) {
        val id = call.paramAsLong("id").orAbort()
        Tasks.delete { it.id eq id }
        call.acknowledge()
    }

    fun update(call: HttpCall) {
        call.validateUsing(UpdateTaskGuard::class) {
            val task = commit()
            logTaskActivity(task, mapOf("action" to "updated task", "title" to task.body))
            call.acknowledge()
        }
    }

    private fun logTaskActivity(task: Task, payload: Map<String, Any?>) {
        val now = call.nowInCurrentTimezone().toInstant()
        val user = caller<User>()
        Activities.insert {
            it.payload to payload
            it.projectId to task.project.id
            it.userId to user.id
            it.taskId to task.id
            it.createdAt to now
            it.updatedAt to now
        }
    }
}
