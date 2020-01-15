package dev.alpas.fireplace.database.migrations

import dev.alpas.fireplace.entities.ProjectMemberships
import dev.alpas.ozone.migration.Migration

class CreateProjectMembershipsTable : Migration() {
    override fun up() {
        createTable(ProjectMemberships)
    }

    override fun down() {
        dropTable(ProjectMemberships)
    }
}
