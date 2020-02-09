package dev.alpas.fireplace.entities

import dev.alpas.JsonSerializable
import dev.alpas.JsonSerializer
import dev.alpas.ozone.Ozone
import dev.alpas.ozone.OzoneTable
import dev.alpas.ozone.bigIncrements
import me.liuwj.ktorm.schema.boolean
import me.liuwj.ktorm.schema.long
import me.liuwj.ktorm.schema.text
import java.time.Instant

interface Task : Ozone<Task>, JsonSerializable {
    val id: Long
    val body: String
    val completed: Boolean
    val createdAt: Instant?
    val updatedAt: Instant?
    val project: Project

    companion object : Ozone.Of<Task>()

    override fun toJson(): String {
        return JsonSerializer.serialize(this)
    }
}

object Tasks : OzoneTable<Task>("tasks") {
    val id by bigIncrements().bindTo { it.id }
    val body by text("body").bindTo { it.body }
    val completed by boolean("completed").default(false).bindTo { it.completed }
    val projectId by long("project_id").references(Projects) { it.project }
    val createdAt by createdAt()
    val updatedAt by updatedAt()
}
