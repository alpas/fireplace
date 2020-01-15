package dev.alpas.fireplace.entities

import me.liuwj.ktorm.entity.Entity

inline fun <reified T> Entity<*>.lazyFetch(name: String, loader: () -> T): T {
    return this[name] as? T ?: loader().also { this[name] = it }
}
