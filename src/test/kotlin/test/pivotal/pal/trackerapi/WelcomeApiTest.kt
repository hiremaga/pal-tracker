package test.pivotal.pal.trackerapi

import io.pivotal.pal.tracker.PalTrackerApplication
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.junit4.SpringRunner

import org.assertj.core.api.Assertions.assertThat
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.client.RestTemplateBuilder
import org.junit.Before
import org.springframework.boot.context.embedded.LocalServerPort

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [(PalTrackerApplication::class)], webEnvironment = RANDOM_PORT)
class WelcomeApiTest {

    @LocalServerPort
    private val port: String? = null

    @Autowired
    private var restTemplate: TestRestTemplate? = null

    @Before
    fun setUp() {
        val builder = RestTemplateBuilder()
                .rootUri("http://localhost:$port")
                .basicAuthorization("user", "password")

        restTemplate = TestRestTemplate(builder)
    }

    @Test
    fun exampleTest() {
        val body = restTemplate!!.getForObject("/", String::class.java)
        assertThat(body).isEqualTo("Hello from test")
    }
}
