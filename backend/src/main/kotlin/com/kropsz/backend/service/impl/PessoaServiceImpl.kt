package com.kropsz.backend.service.impl

import com.kropsz.backend.domain.Pessoa
import com.kropsz.backend.exception.PessoaNotFoundException
import com.kropsz.backend.repository.PessoaRepository
import com.kropsz.backend.service.PessoaService
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class PessoaServiceImpl(val pessoaRepository: PessoaRepository) : PessoaService {

    override fun listarPessoas(): List<Pessoa> {
        return pessoaRepository.findAll()
    }

    override fun buscarPessoaPorId(id: Int): Pessoa {
        return pessoaRepository.findById(id)
            .orElseThrow { PessoaNotFoundException("Pessoa com ID $id não encontrada") }
    }


    override fun buscarPessoaPorNome(nome: String): List<Pessoa> {
        require(nome.isNotBlank()) { "O nome não pode ser nulo ou vazio." }
        return pessoaRepository.findByNome(nome)
    }

    override fun salvarPessoa(pessoa: Pessoa): Pessoa {
        return pessoaRepository.save(pessoa)
    }

    override fun atualizarPessoa(id: Int, pessoa: Pessoa): Pessoa {
        val pessoaSalva = pessoaRepository.findById(id)
            .orElseThrow { PessoaNotFoundException("Pessoa com ID $id não encontrada") }

        pessoaSalva.nome = pessoa.nome
        pessoaSalva.rua = pessoa.rua
        pessoaSalva.numero = pessoa.numero
        pessoaSalva.complemento = pessoa.complemento
        pessoaSalva.cidade = pessoa.cidade

        pessoaSalva.updatedAt = Instant.now()

        return pessoaRepository.save(pessoaSalva)
    }

    override fun deletarPessoa(id: Int) {
        pessoaRepository.deleteById(id)
    }
}