import dev.alpas.fireplace.entities.Project
import dev.alpas.fireplace.entities.Projects
import dev.alpas.fireplace.entities.User
import dev.alpas.fireplace.entities.Users
import dev.alpas.hashing.Hasher
import dev.alpas.make
import dev.alpas.ozone.create
import dev.alpas.pulsar.EntityFactory
import dev.alpas.pulsar.app
import dev.alpas.pulsar.faker
import java.time.Instant

internal class UserFactory : EntityFactory<User> {
    override operator fun invoke(attrs: Map<String, Any?>): User {
        val now = Instant.now()
        val password = attrs.getOrDefault(Users.password.name, "secret").toString()
        return Users.create {
            it.name to attrs.getOrDefault(Users.name.name, faker.name().fullName())
            it.password to app.make<Hasher>().hash(password)
            it.email to attrs.getOrDefault(Users.email.name, faker.internet().emailAddress())
            it.createdAt to attrs.getOrDefault(Users.createdAt.name, now)
            it.updatedAt to attrs.getOrDefault(Users.updatedAt.name, now)
        }
    }
}

internal class ProjectFactory : EntityFactory<Project> {
    override fun invoke(attrs: Map<String, Any?>): Project {
        val now = Instant.now()
        return Projects.create {
            it.title to attrs.getOrDefault(Projects.title.name, faker.lorem().sentence())
            it.description to attrs.getOrDefault(Projects.description.name, faker.lorem().paragraph())
            it.ownerId to attrs["owner_id"]
            it.createdAt to now
            it.updatedAt to now
        }
    }
}
