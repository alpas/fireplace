package dev.alpas.fireplace.entities

import dev.alpas.ozone.MigratingTable
import dev.alpas.ozone.bigIncrements
import me.liuwj.ktorm.entity.Entity
import me.liuwj.ktorm.schema.long
import me.liuwj.ktorm.schema.timestamp
import java.time.Instant

interface ProjectMembership : Entity<ProjectMembership> {
    val id: Long
    val project: Project
    val member: User
    val createdAt: Instant?
    val updatedAt: Instant?

    companion object : Entity.Factory<ProjectMembership>()
}

object ProjectMemberships : MigratingTable<ProjectMembership>("project_members") {
    val id by bigIncrements("id").bindTo { it.id }
    val userId by long("user_id").references(Users) { it.member }
    val projectId by long("project_id").references(Projects) { it.project }
    val createdAt by timestamp("created_at").nullable().bindTo { it.createdAt }
    val updatedAt by timestamp("updated_at").nullable().bindTo { it.updatedAt }
}
