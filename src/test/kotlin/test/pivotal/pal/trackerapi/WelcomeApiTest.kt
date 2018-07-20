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

@RunWith(SpringRunner::class)
@SpringBootTest(classes = [(PalTrackerApplication::class)], webEnvironment = RANDOM_PORT)
class WelcomeApiTest {

    @Autowired
    private val restTemplate: TestRestTemplate? = null

    @Test
    fun exampleTest() {
        val body = this.restTemplate!!.getForObject("/", String::class.java)
        assertThat(body).isEqualTo("Hello from test")
    }
}
