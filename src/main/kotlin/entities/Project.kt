package dev.alpas.fireplace.entities

import dev.alpas.ozone.MigratingTable
import dev.alpas.ozone.bigIncrements
import dev.alpas.ozone.mediumText
import me.liuwj.ktorm.dsl.eq
import me.liuwj.ktorm.entity.Entity
import me.liuwj.ktorm.entity.findList
import me.liuwj.ktorm.schema.long
import me.liuwj.ktorm.schema.text
import me.liuwj.ktorm.schema.timestamp
import java.time.Instant

interface Project : Entity<Project> {
    val id: Long
    val title: String
    val description: String
    val notes: String?
    val createdAt: Instant?
    val updatedAt: Instant?
    val owner: User
    val members: List<User>
        get() = lazyFetch("members") {
            ProjectMemberships.findList { it.projectId eq id }.map { it.member }
        }

    val activities: List<Activity>
        get() = lazyFetch("activities") {
            Activities.findList { it.projectId eq id }
        }

    val tasks: List<Task>
        get() = lazyFetch("tasks") {
            Tasks.findList { it.projectId eq id }.toList()
        }

    companion object : Entity.Factory<Project>()
}

object Projects : MigratingTable<Project>("projects") {
    val id by bigIncrements("id").bindTo { it.id }
    val title by mediumText("title").bindTo { it.title }
    val description by text("description").bindTo { it.description }
    val notes by text("notes").nullable().bindTo { it.notes }
    val ownerId by long("owner_id").references(Users) { it.owner }
    val createdAt by timestamp("created_at").nullable().bindTo { it.createdAt }
    val updatedAt by timestamp("updated_at").nullable().bindTo { it.updatedAt }
}
