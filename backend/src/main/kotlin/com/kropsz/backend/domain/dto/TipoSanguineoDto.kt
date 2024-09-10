package com.kropsz.backend.domain.dto

import jakarta.validation.constraints.NotBlank

data class TipoSanguineoDto (

    @NotBlank(message = "Tipo sanguíneo não pode ser vazio")
    val tipo: String,

    @NotBlank(message = "Fator não pode ser vazio")
    val fator: String
)