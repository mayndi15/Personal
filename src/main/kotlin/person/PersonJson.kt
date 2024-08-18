package com.salus.person

import com.fasterxml.jackson.annotation.JsonIgnore

data class PersonJson(

    @JsonIgnore
    val id: Long,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val createdDate: String,
    val updatedDate: String
)