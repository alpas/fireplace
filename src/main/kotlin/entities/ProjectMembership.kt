package dev.alpas.fireplace.entities

import dev.alpas.ozone.Ozone
import dev.alpas.ozone.OzoneTable
import dev.alpas.ozone.bigIncrements
import me.liuwj.ktorm.schema.long
import java.time.Instant

interface ProjectMembership : Ozone<ProjectMembership> {
    val id: Long
    val project: Project
    val member: User
    val createdAt: Instant?
    val updatedAt: Instant?

    companion object : Ozone.Of<ProjectMembership>()
}

object ProjectMemberships : OzoneTable<ProjectMembership>("project_members") {
    val id by bigIncrements().bindTo { it.id }
    val userId by long("user_id").belongsTo(Users) { it.member }
    val projectId by long("project_id").belongsTo(Projects) { it.project }
    val createdAt by createdAt()
    val updatedAt by updatedAt()
}
