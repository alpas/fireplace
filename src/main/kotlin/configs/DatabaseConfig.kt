package dev.alpas.fireplace.configs

import dev.alpas.Environment
import dev.alpas.ozone.ConnectionConfig
import dev.alpas.ozone.DatabaseConfig
import dev.alpas.ozone.MySqlConnection
import dev.alpas.ozone.MySqlDialect

@Suppress("unused")
class DatabaseConfig(env: Environment) : DatabaseConfig(env) {
    init {
        addConnections(env)
    }

    private fun addConnections(env: Environment) {
        addConnection("mysql", lazy { MySqlConnection(env, ConnectionConfig(sqlDialect = MySqlDialect())) })
    }
}

