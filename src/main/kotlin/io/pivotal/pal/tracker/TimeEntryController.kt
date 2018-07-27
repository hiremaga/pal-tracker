package io.pivotal.pal.tracker

import org.springframework.boot.actuate.metrics.CounterService
import org.springframework.boot.actuate.metrics.GaugeService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TimeEntryController(
        private val timeEntryRepository: TimeEntryRepository,
        private val counterService: CounterService,
        private val gaugeService: GaugeService) {

    @PostMapping("/time-entries")
    fun create(@RequestBody timeEntry: TimeEntry): ResponseEntity<TimeEntry> {
        counterService.increment("TimeEntry.created")
        gaugeService.submit("timeEntries.count", timeEntryRepository.list().size.toDouble())
        return ResponseEntity(timeEntryRepository.create(timeEntry), HttpStatus.CREATED)
    }

    @GetMapping("/time-entries/{id}")
    fun read(@PathVariable id: Long): ResponseEntity<TimeEntry?> {
        val timeEntry = timeEntryRepository.find(id)
        return if (timeEntry != null) {
            counterService.increment("TimeEntry.read")
            ResponseEntity(timeEntry, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @GetMapping("/time-entries")
    fun list(): ResponseEntity<List<TimeEntry>> {
        counterService.increment("TimeEntry.listed")
        return ResponseEntity(timeEntryRepository.list(), HttpStatus.OK)
    }

    @PutMapping("/time-entries/{id}")
    fun update(@PathVariable id: Long, @RequestBody timeEntry: TimeEntry): ResponseEntity<TimeEntry> {
        val updatedTimeEntry = timeEntryRepository.update(id, timeEntry)
        return if (updatedTimeEntry != null) {
            counterService.increment("TimeEntry.updated")
            ResponseEntity(updatedTimeEntry, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/time-entries/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        counterService.increment("TimeEntry.deleted");
        gaugeService.submit("timeEntries.count", timeEntryRepository.list().size.toDouble())
        timeEntryRepository.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}