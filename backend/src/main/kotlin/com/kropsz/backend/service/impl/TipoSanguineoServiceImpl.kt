package com.kropsz.backend.service.impl


import com.kropsz.backend.domain.TipoSanguineo
import com.kropsz.backend.domain.dto.TipoSanguineoDto
import com.kropsz.backend.exception.EntityNotFound
import com.kropsz.backend.repository.TipoSanguineoRepository
import com.kropsz.backend.service.TipoSanguineoService
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class TipoSanguineoServiceImpl(
    private val tipoSanguineoRepository: TipoSanguineoRepository
) : TipoSanguineoService {

    override fun buscarTipoSanguineo(id: Int): TipoSanguineo {
        return tipoSanguineoRepository.findById(id)
            .orElseThrow { throw EntityNotFound("Tipo sanguíneo não encontrado") }
    }

    override fun buscarTipoSanguineoPeloFator(fator: String): TipoSanguineo {
        return tipoSanguineoRepository.findByFator(fator) ?: throw EntityNotFound("Tipo sanguíneo não encontrado")}

    override fun buscarTodosTiposSanguineos(): List<TipoSanguineo> {
        return tipoSanguineoRepository.findAll()
    }

    override fun adicionarTipoSanguineo(tipoSanguineo: TipoSanguineoDto): TipoSanguineo {
        val novoTipoSanguineo = TipoSanguineo(
            tipo = tipoSanguineo.tipo,
            fator = tipoSanguineo.fator,
            createdAt = Instant.now(),
            updatedAt = null
        )
        return tipoSanguineoRepository.save(novoTipoSanguineo)
    }

    override fun atualizarTipoSanguineo(id: Int, tipoSanguineo: TipoSanguineoDto): TipoSanguineo {
        val tipoSanguineoExistente = buscarTipoSanguineo(id)
        tipoSanguineoExistente.tipo = tipoSanguineo.tipo
        tipoSanguineoExistente.fator = tipoSanguineo.fator
        tipoSanguineoExistente.updatedAt = Instant.now()
        return tipoSanguineoRepository.save(tipoSanguineoExistente)
    }

    override fun deletarTipoSanguineo(id: Int) {
        val tipoSanguineo = buscarTipoSanguineo(id)
        tipoSanguineoRepository.delete(tipoSanguineo)
    }
}