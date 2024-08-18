package person

import com.salus.person.Gender
import com.salus.person.Person
import com.salus.person.PersonRepository
import com.salus.person.PersonService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import java.time.LocalDateTime

class PersonServiceTest {

    private lateinit var repository: PersonRepository
    private lateinit var service: PersonService

    @BeforeEach
    fun setUp() {
        repository = mockk()
        service = PersonService(repository)
    }

    @Test
    fun `should load person by id`() {
        val id = 1L
        every { repository.existsById(id) } returns true

        val result = service.load(id)

        assertTrue(result)
        verify(exactly = 1) { repository.existsById(id) }
    }

    @Test
    fun `should create person`() {
        val person = Person(firstName = "Adam", lastName = "Adams", gender = Gender.MALE)
        val saved = person.copy(id = 1, createdDate = LocalDateTime.now(), updatedDate = LocalDateTime.now())

        every { repository.save(any<Person>()) } returns saved

        val result = service.create(person)

        verify(exactly = 1) { repository.save(any<Person>()) }

        assertNotNull(result.id)
        assertNotNull(result.createdDate)
        assertNotNull(result.updatedDate)
        assertEquals("Adam", result.firstName)
        assertEquals("Adams", result.lastName)
        assertEquals("MALE", result.gender.toString())
    }
}