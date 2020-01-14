import dev.alpas.fireplace.ConsoleKernel
import dev.alpas.fireplace.addRoutes
import dev.alpas.pulsar.TestBase
import dev.alpas.routing.Router

abstract class TestBase : TestBase(ConsoleKernel::class.java) {
    override fun Router.loadRoutes() {
        addRoutes()
    }
}
