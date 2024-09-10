package com.kropsz.backend.service

import com.kropsz.backend.domain.Cidade
import com.kropsz.backend.domain.dto.CidadeDto

interface CidadeService {

    fun listarCidades(): List<Cidade>

    fun buscarCidadePorId(id: Int): Cidade

    fun buscarCidadePorNome(nome: String): List<Cidade>

    fun salvarCidade(cidade: CidadeDto): Cidade

    fun atualizarCidade(id: Int, cidade: CidadeDto): Cidade

    fun deletarCidade(id: Int)
}