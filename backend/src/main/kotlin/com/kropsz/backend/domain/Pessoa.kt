package com.kropsz.backend.domain

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "pessoas")
data class Pessoa(

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

    @Column(name = "rg")
    val rg: String,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "cidade_id")
    var cidade: Cidade,

    @ManyToOne(cascade = [CascadeType.ALL])
    @JoinColumn(name = "tipo_id")
    val tipoSanguineo: TipoSanguineo,

    @ManyToMany
    @JoinTable(
        name = "pessoa_doacao",
        joinColumns = [JoinColumn(name = "pessoa_id")],
        inverseJoinColumns = [JoinColumn(name = "doacao_id")]
    )
    var doacoes: Set<Doacoes> = HashSet(),

    @Column(name = "created_at")
    val createdAt: Instant,

    @Column(name = "updated_at")
    var updatedAt: Instant

)