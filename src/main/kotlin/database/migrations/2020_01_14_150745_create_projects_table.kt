package dev.alpas.fireplace.database.migrations

import dev.alpas.fireplace.entities.Projects
import dev.alpas.ozone.migration.Migration

class CreateProjectsTable : Migration() {
    override fun up() {
        createTable(Projects)
    }
    override fun down() {
        dropTable(Projects)
    }
}