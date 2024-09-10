package com.kropsz.backend.service

import com.kropsz.backend.domain.Doacoes
import com.kropsz.backend.domain.dto.DoacoesDto

interface DoacoesService {

    fun adicionarDoacao(doacao : DoacoesDto) : Doacoes

    fun listarDoacoes() : List<Doacoes>

    fun buscarDoacao(id : Int) : Doacoes

    fun atualizarDoacao(id: Int, doacao : DoacoesDto) : Doacoes

    fun deletarDoacao(id : Int)
}