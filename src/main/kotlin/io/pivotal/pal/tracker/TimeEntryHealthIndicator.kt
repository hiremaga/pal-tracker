package io.pivotal.pal.tracker

import org.springframework.boot.actuate.health.Health
import org.springframework.boot.actuate.health.HealthIndicator
import org.springframework.stereotype.Component

@Component
class TimeEntryHealthIndicator(private val timeEntryRepository: TimeEntryRepository) : HealthIndicator {
    override fun health(): Health {
        return if (timeEntryRepository.list().size < 5) {
            Health.down().build()
        } else {
            Health.up().build()
        }
    }
}