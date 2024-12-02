package com.salus.com.salus.person

import com.salus.com.salus.utils.JsonUtils
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/person")
class PersonController(private val service: PersonService, private val converter: PersonConverter) {

    @PostMapping
    fun create(@RequestBody body: String): ResponseEntity<PersonJson> {
        val pj: PersonJson = JsonUtils.mapper(body)

        var p = converter.toModel(pj, null)
        p = service.create(p)

        val response = converter.toJson(p)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody body: String): ResponseEntity<PersonJson> {
        val pj: PersonJson = JsonUtils.mapper(body)

        var p = converter.toModel(pj, id)
        p = service.update(id, p)

        val response = converter.toJson(p)
        return ResponseEntity.status(HttpStatus.OK).body(response)
    }

    @GetMapping("/{id}")
    fun details(@PathVariable id: Long): ResponseEntity<Person> {
        val person = service.details(id)
        return ResponseEntity.ok(person)
    }

    @GetMapping
    fun list(): List<Person> {
        return service.list()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/health-check")
    fun healthCheck(): HttpStatus {
        return HttpStatus.I_AM_A_TEAPOT
    }
}