import dev.alpas.fireplace.entities.Activities
import dev.alpas.fireplace.entities.Projects
import dev.alpas.orAbort
import dev.alpas.ozone.faker
import dev.alpas.ozone.from
import dev.alpas.pulsar.RefreshDatabase
import dev.alpas.pulsar.trapRedirects
import io.restassured.RestAssured.get
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import me.liuwj.ktorm.dsl.count
import me.liuwj.ktorm.dsl.eq
import me.liuwj.ktorm.entity.findOne
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ProjectAddTest : TestBase(), RefreshDatabase {
    @Test
    fun `unauthenticated user cannot access the add page`() {
        Given {
            trapRedirects()
        } When {
            get("projects/create")
        } Then {
            assertRedirect("/login", 302)
        }
    }

    @Test
    fun `an authorized user can access project add page`() {
        asAuthorizedUser { get("projects/create") }
        assertViewIs("project_new")
    }

    @Test
    fun `project title is required`() {
        val user = from(UserFactory)

        Given {
            noCSRFMiddleware()
            formParam("description", faker.lorem().paragraph())
        } When {
            asUser(user).post("projects")
        } Then {
            assertResponseHasErrors(listOf("title"))
            assertResponseHasNoErrors(listOf("description"))
        }
    }

    @Test
    fun `project title must be of proper length`() {
        val user = from(UserFactory)

        Given {
            noCSRFMiddleware()
            formParam("title", "short")
            formParam("description", faker.lorem().paragraph())
        } When {
            asUser(user).post("projects")
        } Then {
            assertResponseHasErrors(mapOf("title" to "The 'title' must be at least 8 characters long."))
        }
    }

    @Test
    fun `project description is required`() {
        val user = from(UserFactory)

        Given {
            noCSRFMiddleware()
            formParam("title", faker.lorem().sentence(5))
        } When {
            asUser(user).post("projects")
        } Then {
            assertResponseHasErrors(listOf("description"))
            assertResponseHasNoErrors(listOf("title"))
        }
    }

    @Test
    fun `user can add a new project`() {
        val user = from(UserFactory)

        val title = faker.lorem().sentence(5)
        val desc = faker.lorem().paragraph()

        Given {
            noCSRFMiddleware()
            formParam("title", title)
            formParam("description", desc)
        } When {
            asUser(user).post("projects")
        }

        val project = Projects.findOne { it.ownerId eq user.id }
        assertNotNull(project)
        assertEquals(title, project?.title)
        assertEquals(desc, project?.description)
    }

    @Test
    fun `user is redirected back to the project list page after creating a project`() {
        Given {
            noCSRFMiddleware()
            formParam("title", "this is a title")
            formParam("description", "desc")
        } When {
            asRandomUser().post("projects")
        }
        assertRedirect(routeNamed("projects.list"))
    }

    @Test
    fun `a project created activity is logged when a new project is created`() {
        val user = from(UserFactory)

        assertEquals(0, Activities.count())

        Given {
            noCSRFMiddleware()
            formParam("title", faker.lorem().sentence(5))
            formParam("description", faker.lorem().paragraph())
        } When {
            asUser(user).post("projects")
        }

        val project = Projects.findOne { it.ownerId eq user.id }.orAbort()
        val activity = Activities.findOne { it.projectId eq project.id }
        assertNotNull(activity)
        assertEquals(user.id, activity?.user?.id)
    }
}
