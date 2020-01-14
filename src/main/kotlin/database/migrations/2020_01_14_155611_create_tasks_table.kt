package dev.alpas.fireplace.database.migrations

import dev.alpas.fireplace.entities.Tasks
import dev.alpas.ozone.migration.Migration

class CreateTasksTable : Migration() {
    override fun up() {
        createTable(Tasks)
    }
    override fun down() {
        dropTable(Tasks)
    }
}