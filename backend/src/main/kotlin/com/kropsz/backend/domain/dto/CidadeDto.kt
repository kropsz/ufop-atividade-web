package com.kropsz.backend.domain.dto

import jakarta.validation.constraints.NotBlank

data class CidadeDto(
    @NotBlank(message = "Nome é obrigatório")
    val nome: String,
    @NotBlank(message = "Estado é obrigatório")
    val estadoId: Int
)
