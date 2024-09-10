package com.kropsz.backend.domain.dto

import jakarta.validation.constraints.NotBlank

data class DoacoesDto(

    @NotBlank(message = "Informe o id da Pessoa")
    val pessoaId: Int,

    @NotBlank(message = "Informe o id do Local")
    val localId: Int,

    @NotBlank(message = "Informe a data da doação")
    val dataDoacao: String

)
