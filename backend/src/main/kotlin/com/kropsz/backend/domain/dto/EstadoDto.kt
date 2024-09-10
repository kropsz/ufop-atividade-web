package com.kropsz.backend.domain.dto

import jakarta.validation.constraints.NotBlank

data class EstadoDto(

    @NotBlank(message = "O Nome do estado é obrigatório")
    val nome: String,

    @NotBlank(message = "A sigla do estado é obrigatória")
    val sigla: String
)
