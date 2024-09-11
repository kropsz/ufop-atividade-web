package com.kropsz.backend.service.impl

import com.kropsz.backend.domain.Estado
import com.kropsz.backend.domain.dto.EstadoDto
import com.kropsz.backend.exception.EntityNotFound
import com.kropsz.backend.repository.EstadoRepository
import com.kropsz.backend.service.EstadoService
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class EstadoServiceImpl(val estadoRepository: EstadoRepository) : EstadoService {

    override fun listarEstados(): List<Estado> {
        return estadoRepository.findAll()
    }

    override fun buscarEstado(id: Int): Estado {
        return estadoRepository.findById(id).orElseThrow { throw EntityNotFound("Estado não encontrado") }
    }

    override fun adicionarEstado(estado: EstadoDto): Estado {
        val novoEstado = Estado(
            nome = estado.nome,
            sigla = estado.sigla,
            createdAt = Instant.now(),
            updatedAt = Instant.now()
        )

        return estadoRepository.save(novoEstado)
    }

    override fun ataualizarEstado(id: Int, estado: EstadoDto): Estado {
        val estadoAtualizado =
            estadoRepository.findById(id).orElseThrow { throw EntityNotFound("Estado não encontrado") }

        estadoAtualizado.nome = estado.nome
        estadoAtualizado.sigla = estado.sigla

        estadoAtualizado.updatedAt = Instant.now()

        return estadoRepository.save(estadoAtualizado)
    }

    override fun deletarEstado(id: Int): Estado {

        val estado = estadoRepository.findById(id).orElseThrow { throw EntityNotFound("Estado não encontrado") }

        estadoRepository.delete(estado)

        return estado

    }


}