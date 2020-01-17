import dev.alpas.auth.Authenticatable
import dev.alpas.fireplace.ConsoleKernel
import dev.alpas.fireplace.addRoutes
import dev.alpas.pulsar.TestBase
import dev.alpas.pulsar.from
import dev.alpas.routing.Router
import io.restassured.specification.RequestSpecification

abstract class TestBase : TestBase(ConsoleKernel::class.java) {
    override fun Router.loadRoutes() {
        addRoutes(app.env)
    }

    fun <T> asRandomUser(block: (user: Authenticatable) -> T): T {
        val user = from(::UserFactory)
        return block(user)
    }

    fun <T> asAuthorizedUser(block: (user: Authenticatable) -> T): T {
        val user = from(::UserFactory)
        becomeUser(user, true)
        return block(user)
    }

    fun RequestSpecification.asRandomUser() = apply {
        becomeUser(from(::UserFactory), true)
    }
}
