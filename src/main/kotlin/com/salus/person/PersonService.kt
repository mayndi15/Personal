package com.salus.com.salus.person

import exceptions.Message
import exceptions.SalusException
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import com.salus.com.salus.kafka.KafkaProducer.publish

@Service
class PersonService(
    private val repository: PersonRepository,
    private val converter: PersonConverter
) {

    fun load(id: Long): Boolean {
        return repository.existsById(id)
    }

    fun create(person: Person): Person {
        setUpTimestamps(person)

        val p = repository.save(person)

        publish(converter.toJson(p))
        return p
    }

    fun update(id: Long, person: Person): Person {
        val persist = details(id)

        setUpdate(person, persist)
        setUpTimestamps(persist)

        val p = repository.save(persist.copy(id = id))

        publish(converter.toJson(p))
        return p
    }

    fun details(id: Long): Person {
        return repository.findById(id).orElseThrow { throw SalusException(Message.ID_NOT_FOUND, "en", id) }
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
}