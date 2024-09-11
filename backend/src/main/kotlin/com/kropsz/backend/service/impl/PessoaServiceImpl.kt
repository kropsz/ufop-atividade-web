package com.kropsz.backend.service.impl

import com.kropsz.backend.domain.Pessoa
import com.kropsz.backend.domain.dto.PessoaDto
import com.kropsz.backend.exception.EntityNotFound
import com.kropsz.backend.repository.CidadeRepository
import com.kropsz.backend.repository.PessoaRepository
import com.kropsz.backend.repository.TipoSanguineoRepository
import com.kropsz.backend.service.PessoaService
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class PessoaServiceImpl(
    val pessoaRepository: PessoaRepository,
    val cidadeRepository: CidadeRepository,
    val tipoSanguineoRepository: TipoSanguineoRepository
) : PessoaService {

    override fun listarPessoas(): List<Pessoa> {
        return pessoaRepository.findAll()
    }

    override fun buscarPessoaPorId(id: Int): Pessoa {
        return pessoaRepository.findById(id)
            .orElseThrow { EntityNotFound("Pessoa com ID $id não encontrada") }
    }

    override fun buscarPessoaPorNome(nome: String): Pessoa {
        require(nome.isNotBlank()) { "O nome não pode ser nulo ou vazio." }
        return pessoaRepository.findByNome(nome)
    }

    override fun salvarPessoa(pessoa: PessoaDto): Pessoa {
        val cidade = cidadeRepository.findById(pessoa.cidadeId)
            .orElseThrow { EntityNotFound("Cidade com ID ${pessoa.cidadeId} não encontrada") }

        val tipoSanguineo = tipoSanguineoRepository.findById(pessoa.tipoSanguineoId)
            .orElseThrow { EntityNotFound("Tipo Sanguíneo com ID ${pessoa.tipoSanguineoId} não encontrado") }

        val pessoaEntity = Pessoa(
            nome = pessoa.nome,
            rua = pessoa.rua,
            numero = pessoa.numero,
            complemento = pessoa.complemento,
            rg = pessoa.rg,
            cidade = cidade,
            tipoSanguineo = tipoSanguineo,
            createdAt = Instant.now(),
            updatedAt = Instant.now()
        )
        return pessoaRepository.save(pessoaEntity)
    }

    override fun atualizarPessoa(id: Int, pessoa: PessoaDto): Pessoa {
        val pessoaSalva = buscarPessoaPorId(id)

        val cidade = cidadeRepository.findById(pessoa.cidadeId)
            .orElseThrow { EntityNotFound("Cidade com ID ${pessoa.cidadeId} não encontrada") }

        pessoaSalva.nome = pessoa.nome
        pessoaSalva.rua = pessoa.rua
        pessoaSalva.numero = pessoa.numero
        pessoaSalva.complemento = pessoa.complemento
        pessoaSalva.cidade = cidade
        pessoaSalva.updatedAt = Instant.now()

        return pessoaRepository.save(pessoaSalva)
    }

    override fun deletarPessoa(id: Int) {
        val pessoaSalva = buscarPessoaPorId(id)
        pessoaRepository.delete(pessoaSalva)
    }
}