package io.pivotal.pal.tracker

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class TimeEntryController(val timeEntryRepository: TimeEntryRepository) {

    @PostMapping("/time-entries")
    fun create(@RequestBody timeEntry: TimeEntry): ResponseEntity<TimeEntry> {
        return ResponseEntity(timeEntryRepository.create(timeEntry), HttpStatus.CREATED)
    }

    @GetMapping("/time-entries/{id}")
    fun read(@PathVariable id: Long): ResponseEntity<TimeEntry?> {
        val timeEntry = timeEntryRepository.find(id)
        return if (timeEntry != null)
            ResponseEntity(timeEntry, HttpStatus.OK)
        else
            ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @GetMapping("/time-entries")
    fun list(): ResponseEntity<List<TimeEntry>> {
        return ResponseEntity(timeEntryRepository.list(), HttpStatus.OK)
    }

    @PutMapping("/time-entries/{id}")
    fun update(@PathVariable id: Long, @RequestBody timeEntry: TimeEntry): ResponseEntity<TimeEntry> {
        val updatedTimeEntry = timeEntryRepository.update(id, timeEntry)
        return if (updatedTimeEntry != null)
            ResponseEntity(updatedTimeEntry, HttpStatus.OK)
        else
            ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @DeleteMapping("/time-entries/{id}")
    fun delete(@PathVariable id: Long) : ResponseEntity<Void> {
        timeEntryRepository.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}