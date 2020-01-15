package dev.alpas.fireplace.database.migrations

import dev.alpas.fireplace.entities.Activities
import dev.alpas.ozone.migration.Migration

class CreateActivitiesTable : Migration() {
    override fun up() {
        createTable(Activities)
    }

    override fun down() {
        dropTable(Activities)
    }
}
