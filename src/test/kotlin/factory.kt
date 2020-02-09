import dev.alpas.fireplace.entities.Project
import dev.alpas.fireplace.entities.Projects
import dev.alpas.fireplace.entities.User
import dev.alpas.fireplace.entities.Users
import dev.alpas.hashing.Hasher
import dev.alpas.make
import dev.alpas.ozone.EntityFactory
import dev.alpas.ozone.faker
import dev.alpas.pulsar.app
import java.time.Instant

internal object UserFactory : EntityFactory<User>() {
    override val table = Users

    override fun entity(): User {
        return User {
            name = faker.name().fullName()
            password = app.make<Hasher>().hash(password)
            email = faker.internet().emailAddress()
            createdAt = Instant.now()
            updatedAt = Instant.now()
        }
    }
}

internal object ProjectFactory : EntityFactory<Project>() {
    override val table = Projects

    override fun entity(): Project {
        return Project {
            title = faker.lorem().sentence()
            description = faker.lorem().paragraph()
            createdAt = Instant.now()
            updatedAt = Instant.now()
        }
    }
}
