package com.kropsz.backend.service

import com.kropsz.backend.domain.Pessoa

interface PessoaService {

    fun listarPessoas(): List<Pessoa>

    fun buscarPessoaPorId(id: Int): Pessoa

    fun buscarPessoaPorNome(nome: String): List<Pessoa>

    fun salvarPessoa(pessoa: Pessoa): Pessoa

    fun atualizarPessoa(id: Int, pessoa: Pessoa): Pessoa

    fun deletarPessoa(id: Int)

}