package com.kropsz.backend.service

import com.kropsz.backend.domain.Pessoa
import com.kropsz.backend.domain.dto.PessoaDto

interface PessoaService {

    fun listarPessoas(): List<Pessoa>

    fun buscarPessoaPorId(id: Int): Pessoa

    fun buscarPessoaPorNome(nome: String): List<Pessoa>

    fun salvarPessoa(pessoa: PessoaDto): Pessoa

    fun atualizarPessoa(id: Int, pessoa: PessoaDto): Pessoa

    fun deletarPessoa(id: Int)

}