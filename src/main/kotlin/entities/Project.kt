package dev.alpas.fireplace.entities

import dev.alpas.ozone.*
import me.liuwj.ktorm.entity.Entity
import me.liuwj.ktorm.schema.long
import me.liuwj.ktorm.schema.text
import java.time.Instant

interface Project : Ozone<Project> {
    val id: Long
    var title: String
    var description: String
    var notes: String?
    var createdAt: Instant?
    var updatedAt: Instant?
    val owner: User

    val members
        get() = hasMany(ProjectMemberships).map { it.member }

    val activities
        get() = hasMany(Activities)

    val tasks
        get() = hasMany(Tasks)

    companion object : Entity.Factory<Project>()
}

object Projects : OzoneTable<Project>("projects") {
    val id by bigIncrements().bindTo { it.id }
    val title by mediumText("title").bindTo { it.title }
    val description by text("description").bindTo { it.description }
    val notes by text("notes").nullable().bindTo { it.notes }
    val ownerId by long("owner_id").belongsTo(Users) { it.owner }
    val createdAt by createdAt()
    val updatedAt by updatedAt()
}
