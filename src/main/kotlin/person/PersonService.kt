package com.salus.person

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PersonService(
    private val repository: PersonRepository, private val kafkaTemplate: KafkaTemplate<String, Any>,
) {

    fun load(id: Long): Boolean {
        return repository.existsById(id)
    }

    fun create(person: Person): Person {
        setUpTimestamps(person)

        val p = repository.save(person)
        publish(p)
        return p
    }

    fun update(id: Long, person: Person): Person {
        val persist = details(id)

        setUpdate(person, persist)
        setUpTimestamps(persist)

        val p = repository.save(persist.copy(id = id))
        publish(p)
        return p
    }

    fun details(id: Long): Person {
        return repository.findById(id).orElse(null)
    }

    fun list(): List<Person> {
        return repository.findAll()
    }

    fun delete(id: Long) {
        if (load(id)) {
            repository.deleteById(id)
        }
    }

    private fun setUpdate(person: Person, personPersist: Person) {
        personPersist.firstName = person.firstName
        personPersist.lastName = person.lastName
        personPersist.gender = person.gender
    }

    private fun setUpTimestamps(person: Person) {
        if (person.createdDate == null) {
            person.createdDate = LocalDateTime.now()
        }
        person.updatedDate = LocalDateTime.now()
    }

    private fun publish(person: Person) {
        kafkaTemplate.send("mongodb-topic", person)
    }
}