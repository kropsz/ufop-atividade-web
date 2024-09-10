package com.kropsz.backend.service.impl

import com.kropsz.backend.domain.Cidade
import com.kropsz.backend.domain.dto.CidadeDto
import com.kropsz.backend.exception.EntityNotFound
import com.kropsz.backend.repository.CidadeRepository
import com.kropsz.backend.repository.EstadoRepository
import com.kropsz.backend.service.CidadeService
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class CidadeServiceImpl(
    val cidadeRepository: CidadeRepository,
    val estadoRepository: EstadoRepository
) : CidadeService {

    override fun listarCidades(): List<Cidade> {
        return cidadeRepository.findAll()
    }

    override fun buscarCidadePorId(id: Int): Cidade {
        return cidadeRepository.findById(id)
            .orElseThrow { EntityNotFound("Cidade com ID $id não encontrada") }
    }

    override fun buscarCidadePorNome(nome: String): List<Cidade> {
        require(nome.isNotBlank()) { "O nome não pode ser nulo ou vazio." }
        return cidadeRepository.findByNome(nome)
    }

    override fun salvarCidade(cidade: CidadeDto): Cidade {
        val estado = estadoRepository.findById(cidade.estadoId)
            .orElseThrow { EntityNotFound("Estado com ID ${cidade.estadoId} não encontrado") }

        val cidadeEntity = Cidade(
            nome = cidade.nome,
            estado = estado,
            createdAt = Instant.now(),
            updatedAt = Instant.now()
        )

        return cidadeRepository.save(cidadeEntity)
    }

    override fun atualizarCidade(id: Int, cidade: CidadeDto): Cidade {
        val cidadeSalva = cidadeRepository.findById(id)
            .orElseThrow { EntityNotFound("Cidade com ID $id não encontrada") }

        cidadeSalva.nome = cidade.nome
        cidadeSalva.updatedAt = Instant.now()

        return cidadeRepository.save(cidadeSalva)
    }

    override fun deletarCidade(id: Int) {
        cidadeRepository.deleteById(id)
    }
}