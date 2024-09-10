package com.kropsz.backend.domain

import jakarta.persistence.*
import java.time.Instant
import java.time.LocalDate

@Entity
@Table(name = "doacoes")
data class Doacoes(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @ManyToMany(mappedBy = "doacoes")
    var pessoas: Set<Pessoa> = HashSet(),

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "local_id")
    var local: LocaisColeta,

    @Column(name = "data")
    var data: LocalDate,

    @Column(name = "created_at")
    val createdAt: Instant,

    @Column(name = "updated_at")
    var updatedAt: Instant
)
