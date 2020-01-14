package dev.alpas.fireplace.controllers

import dev.alpas.http.HttpCall
import dev.alpas.routing.Controller

class HomeController : Controller() {
    fun index(call: HttpCall) {
        call.render("home")
    }
}
