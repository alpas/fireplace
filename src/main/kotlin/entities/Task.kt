package dev.alpas.fireplace.entities

import dev.alpas.JsonSerializable
import dev.alpas.JsonSerializer
import dev.alpas.ozone.MigratingTable
import dev.alpas.ozone.bigIncrements
import me.liuwj.ktorm.entity.Entity
import me.liuwj.ktorm.schema.boolean
import me.liuwj.ktorm.schema.long
import me.liuwj.ktorm.schema.text
import me.liuwj.ktorm.schema.timestamp
import java.time.Instant

interface Task : Entity<Task>, JsonSerializable {
    val id: Long
    val body: String
    val completed: Boolean
    val createdAt: Instant?
    val updatedAt: Instant?
    val project: Project

    companion object : Entity.Factory<Task>()

    override fun toJson(): String {
        return JsonSerializer.serialize(this)
    }
}

object Tasks : MigratingTable<Task>("tasks") {
    val id by bigIncrements("id").bindTo { it.id }
    val body by text("body").bindTo { it.body }
    val completed by boolean("completed").default(false).bindTo { it.completed }
    val projectId by long("project_id").references(Projects) { it.project }
    val createdAt by timestamp("created_at").nullable().bindTo { it.createdAt }
    val updatedAt by timestamp("updated_at").nullable().bindTo { it.updatedAt }
}
