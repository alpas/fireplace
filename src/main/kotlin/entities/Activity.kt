package dev.alpas.fireplace.entities

import dev.alpas.ozone.Ozone
import dev.alpas.ozone.OzoneTable
import dev.alpas.ozone.bigIncrements
import me.liuwj.ktorm.jackson.json
import me.liuwj.ktorm.schema.long
import me.liuwj.ktorm.schema.typeRef
import java.time.Instant

interface Activity : Ozone<Activity> {
    val id: Long
    val payload: Map<String, Any?>
    val project: Project
    val user: User
    val task: Task?
    val createdAt: Instant?
    val updatedAt: Instant?

    companion object : Ozone.Of<Activity>()
}

object Activities : OzoneTable<Activity>("activities") {
    val id by bigIncrements().bindTo { it.id }
    val payload by json("action", typeRef<Map<String, Any?>>()).bindTo { it.payload }
    val projectId by long("project_id").references(Projects) { it.project }
    val userId by long("user_id").references(Users) { it.user }
    val taskId by long("task_id").nullable().references(Tasks) { it.task }
    val createdAt by createdAt()
    val updatedAt by updatedAt()
}
