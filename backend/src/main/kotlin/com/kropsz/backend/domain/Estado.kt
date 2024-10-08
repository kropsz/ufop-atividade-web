package com.kropsz.backend.domain

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "estados")
data class Estado (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(name = "nome")
    var nome: String,

    @Column(name = "sigla")
    var sigla: String,

    @Column(name = "created_at")
    val createdAt: Instant,

    @Column(name = "updated_at")
    var updatedAt: Instant

)