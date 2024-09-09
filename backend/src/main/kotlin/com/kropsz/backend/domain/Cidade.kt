package com.kropsz.backend.domain

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "cidades")
data class Cidade(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int,

    @Column(name = "nome")
    var nome: String,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "estados_id")
    val estado: Estado,

    @Column(name = "created_at")
    val createdAt: Instant,

    @Column(name = "updated_at")
    var updatedAt: Instant

)
