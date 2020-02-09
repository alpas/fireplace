package dev.alpas.fireplace.entities

import dev.alpas.auth.Authenticatable
import dev.alpas.auth.BaseUser
import dev.alpas.auth.BaseUsersTable
import dev.alpas.auth.UserProvider
import dev.alpas.ozone.Ozone
import dev.alpas.ozone.hasMany
import dev.alpas.ozone.hasOne

interface User : BaseUser<User>, Authenticatable {
    override val mustVerifyEmail get() = false
    val libraryCard get() = hasOne(LibraryCards)
    val projects get() = hasMany(Projects, "owner_id")
    val membershipProjects get() = hasMany(ProjectMemberships).map { it.project }

    companion object : Ozone.Of<User>()
}

object Users : BaseUsersTable<User>(), UserProvider
