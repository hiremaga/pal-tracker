package test.pivotal.pal.tracker

import io.pivotal.pal.tracker.WelcomeController
import org.junit.Test

import org.assertj.core.api.Assertions.assertThat

class WelcomeControllerTest {
    @Test
    fun itSaysHello() {
        val controller = WelcomeController("A welcome message")

        assertThat(controller.sayWelcomeMessage()).isEqualTo("A welcome message")
    }
}
