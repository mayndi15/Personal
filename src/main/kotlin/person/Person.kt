package com.salus.person

import jakarta.persistence.Entity
import jakarta.persistence.Table
import jakarta.persistence.Id
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Column
import jakarta.persistence.Enumerated
import jakarta.persistence.EnumType

import java.time.LocalDateTime

@Entity
@Table(name = "person")
data class Person(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0,

    @Column(name = "first_name", nullable = false)
    var firstName: String? = null,

    @Column(name = "last_name", nullable = true)
    var lastName: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    var gender: Gender? = null,

    @Column(name = "created_date", nullable = false)
    var createdDate: LocalDateTime? = null,

    @Column(name = "updated_date", nullable = false)
    var updatedDate: LocalDateTime? = null
)