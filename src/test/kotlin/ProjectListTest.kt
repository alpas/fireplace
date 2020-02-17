import dev.alpas.fireplace.entities.Project
import dev.alpas.ozone.from
import dev.alpas.pulsar.RefreshDatabase
import dev.alpas.pulsar.trapRedirects
import io.restassured.RestAssured.get
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectListTest : TestBase(), RefreshDatabase {
    @Test
    fun `home page is redirected to project list page`() {
        Given {
            redirects().follow(false)
        } When {
            get("/home")
        } Then {
            assertRedirect(routeNamed("projects.list"), 302)
        }
    }

    @Test
    fun `unauthenticated user is redirected to the login page`() {
        Given {
            trapRedirects()
        } When {
            asRandomUser { get("/projects") }
        } Then {
            assertRedirect("/login", 302)
        }
    }

    @Test
    fun `an authorized user can access project list page`() {
        asAuthorizedUser { get("projects") }
        assertViewIs("project_list")
        assertViewHas(mapOf("projects" to emptyList<Project>()))
    }

    @Test
    fun `user's projects are listed`() {
        val user = from(UserFactory)
        val projects = from(ProjectFactory, 3, mapOf("owner_id" to user.id))

        Given {
            asUser(user)
        } When {
            get("/projects")
        } Then {
            assertViewIs("project_list")
            val actualProjects = viewArgs()?.get("projects") as? List<Project>
            assertEquals(projects.size, actualProjects?.size)
            assertEquals(projects.map { it.id }, actualProjects?.map { it.id })
        }
    }

    @Test
    fun `other users' projects are not listed`() {
        val me = from(UserFactory)
        val myProjects = from(ProjectFactory, 4, mapOf("owner_id" to me.id))

        from(ProjectFactory, 2, mapOf("owner_id" to from(UserFactory).id))
        from(ProjectFactory, 3, mapOf("owner_id" to from(UserFactory).id))

        Given {
            asUser(me)
        } When {
            get("/projects")
        } Then {
            assertViewIs("project_list")
            val actualProjects = viewArgs()?.get("projects") as? List<Project>
            assertEquals(myProjects.size, actualProjects?.size)
            assertEquals(myProjects.map { it.id }, actualProjects?.map { it.id })
        }
    }
}

