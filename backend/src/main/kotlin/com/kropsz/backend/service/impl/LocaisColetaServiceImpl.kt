package com.kropsz.backend.service.impl

import com.kropsz.backend.domain.LocaisColeta
import com.kropsz.backend.domain.dto.LocaisColetaDto
import com.kropsz.backend.exception.EntityNotFound
import com.kropsz.backend.repository.CidadeRepository
import com.kropsz.backend.repository.LocaisColetaRepository
import com.kropsz.backend.service.LocaisColetaService
import org.springframework.stereotype.Service
import java.time.Instant

@Service
class LocaisColetaServiceImpl (
    val locaisColetaRepository: LocaisColetaRepository,
    val cidadeRepository: CidadeRepository
) : LocaisColetaService {

    override fun buscarLocaisColeta(): List<LocaisColeta> {
        return locaisColetaRepository.findAll()
    }

    override fun buscarLocalColeta(id: Int): LocaisColeta {
        return locaisColetaRepository.findById(id)
            .orElseThrow { throw EntityNotFound("Local de coleta não encontrado") }
    }

    override fun adicionarLocalColeta(localColeta: LocaisColetaDto): LocaisColeta {
        val cidade = cidadeRepository.findById(localColeta.cidadeId)
            .orElseThrow { throw EntityNotFound("Cidade não encontrada") }

        val novoLocalColeta = LocaisColeta(
            nome = localColeta.nome,
            rua = localColeta.rua,
            numero = localColeta.numero,
            complemento = localColeta.complemento,
            cidade = cidade,
            createdAt = Instant.now(),
            updatedAt = Instant.now()
        )

        return locaisColetaRepository.save(novoLocalColeta)
    }

    override fun deletarLocalColeta(id: Int) {
        locaisColetaRepository.deleteById(id)
    }

    override fun atualizarLocalColeta(id: Int, localColeta: LocaisColetaDto): LocaisColeta {
        val cidade = cidadeRepository.findById(localColeta.cidadeId)
            .orElseThrow { throw EntityNotFound("Cidade não encontrada") }

        val localColetaAtualizado = locaisColetaRepository.findById(id)
            .orElseThrow { throw EntityNotFound("Local de coleta não encontrado") }

        localColetaAtualizado.nome = localColeta.nome
        localColetaAtualizado.rua = localColeta.rua
        localColetaAtualizado.numero = localColeta.numero
        localColetaAtualizado.complemento = localColeta.complemento
        localColetaAtualizado.cidade = cidade
        localColetaAtualizado.updatedAt = Instant.now()

        return locaisColetaRepository.save(localColetaAtualizado)


    }

}