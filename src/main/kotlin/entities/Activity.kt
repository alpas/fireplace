package dev.alpas.fireplace.entities

import dev.alpas.ozone.MigratingTable
import dev.alpas.ozone.bigIncrements
import me.liuwj.ktorm.entity.Entity
import me.liuwj.ktorm.jackson.json
import me.liuwj.ktorm.schema.long
import me.liuwj.ktorm.schema.timestamp
import me.liuwj.ktorm.schema.typeRef
import java.time.Instant

interface Activity : Entity<Activity> {
    val id: Long
    val payload: Map<String, Any?>
    val project: Project
    val user: User
    val task: Task?
    val createdAt: Instant?
    val updatedAt: Instant?

    companion object : Entity.Factory<Activity>()
}

object Activities : MigratingTable<Activity>("activities") {
    val id by bigIncrements("id").bindTo { it.id }
    val payload by json("action", typeRef<Map<String, Any?>>()).bindTo { it.payload }
    val projectId by long("project_id").references(Projects) { it.project }
    val userId by long("user_id").references(Users) { it.user }
    val taskId by long("task_id").nullable().references(Tasks) { it.task }
    val createdAt by timestamp("created_at").nullable().bindTo { it.createdAt }
    val updatedAt by timestamp("updated_at").nullable().bindTo { it.updatedAt }
}
