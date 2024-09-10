package com.kropsz.backend.domain.dto

import jakarta.validation.constraints.NotBlank

data class LocaisColetaDto(

    @NotBlank(message = "Nome é obrigatório")
    val nome: String,

    @NotBlank(message = "O campo 'rua' é obrigatório")
    val rua: String,

    @NotBlank(message = "O campo 'numero' é obrigatório")
    val numero: String,

    @NotBlank(message = "O campo 'complemento' é obrigatório")
    val complemento: String,

    @NotBlank(message = "O campo 'cidade' é obrigatório")
    val cidadeId: Int,
)
