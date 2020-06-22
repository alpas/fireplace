package dev.alpas.fireplace

import dev.alpas.Alpas
import dev.alpas.make
import dev.alpas.routing.Router

fun main(args: Array<String>) = Alpas(args) { make<Router>().addRoutes(env) }.ignite()
