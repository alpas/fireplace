package dev.alpas.fireplace.controllers

import dev.alpas.fireplace.entities.Tasks
import dev.alpas.fireplace.guards.CreateTaskGuard
import dev.alpas.fireplace.guards.UpdateTaskGuard
import dev.alpas.http.HttpCall
import dev.alpas.orAbort
import dev.alpas.routing.Controller
import me.liuwj.ktorm.dsl.delete
import me.liuwj.ktorm.dsl.eq

class TaskController : Controller() {
    fun store(call: HttpCall) {
        call.validateUsing(CreateTaskGuard::class) {
            val task = commit()
            call.replyAsJson(task)
        }
    }

    fun delete(call: HttpCall) {
        val id = call.paramAsLong("id").orAbort()
        Tasks.delete { it.id eq id }
        call.acknowledge()
    }

    fun update(call: HttpCall) {
        call.validateUsing(UpdateTaskGuard::class).commit()
        call.acknowledge()
    }
}
