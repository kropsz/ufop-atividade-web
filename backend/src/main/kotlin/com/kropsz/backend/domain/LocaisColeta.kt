package com.kropsz.backend.domain

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "locais_coleta")
data class LocaisColeta(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(name = "nome")
    var nome: String,

    @Column(name = "rua")
    var rua: String,

    @Column(name = "numero")
    var numero: String,

    @Column(name = "complemento")
    var complemento: String,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "cidade_id")
    var cidade: Cidade,

    @Column(name = "created_at")
    val createdAt: Instant,

    @Column(name = "updated_at")
    var updatedAt: Instant
)
