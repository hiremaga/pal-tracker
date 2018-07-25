package io.pivotal.pal.tracker

class InMemoryTimeEntryRepository: TimeEntryRepository {
    private val timeEntries = mutableMapOf<Long, TimeEntry>()
    private var nextId = 1L

    override fun create(timeEntry: TimeEntry): TimeEntry {
        val createdTimeEntry = timeEntry.copy(id=nextId++)
        timeEntries[createdTimeEntry.id!!] = (createdTimeEntry)
        return createdTimeEntry
    }

    override fun find(id: Long) = timeEntries[id]

    override fun list() = timeEntries.values.toList()

    override fun update(id: Long, timeEntry: TimeEntry): TimeEntry {
        val updatedTimeEntry = timeEntry.copy(id=id)
        timeEntries[updatedTimeEntry.id!!] = updatedTimeEntry
        return updatedTimeEntry
    }

    override fun delete(id: Long) {
       timeEntries.remove(id)
    }
}