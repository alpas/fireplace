package dev.alpas.fireplace.controllers

import dev.alpas.fireplace.guards.CreateTaskGuard
import dev.alpas.http.HttpCall
import dev.alpas.routing.Controller

class TaskController : Controller() {
    fun store(call: HttpCall) {
        call.validateUsing(CreateTaskGuard::class) {
            val task = commit()
            call.replyAsJson(task)
        }
    }
}
