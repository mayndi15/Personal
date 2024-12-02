package com.salus.com.salus.person

import com.salus.com.salus.utils.DateUtils
import org.springframework.stereotype.Component

@Component
class PersonConverter {

    fun toModel(pj: PersonJson, id: Long?): Person {
        val id = id ?: 0L
        return Person(
            id = id,
            firstName = pj.firstName,
            lastName = pj.lastName,
            gender = Gender.valueOf(pj.gender)
        )
    }

    fun toJson(p: Person): PersonJson {
        return PersonJson(
            id = p.id,
            firstName = p.firstName ?: "",
            lastName = p.lastName ?: "",
            gender = p.gender.toString(),
            createdDate = p.createdDate?.let { DateUtils.localDateTimeToString(it) } ?: "",
            updatedDate = p.updatedDate?.let { DateUtils.localDateTimeToString(it) } ?: ""
        )
    }
}