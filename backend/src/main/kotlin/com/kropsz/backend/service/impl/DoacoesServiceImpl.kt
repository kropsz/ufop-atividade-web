package com.kropsz.backend.service.impl

import com.kropsz.backend.domain.Doacoes
import com.kropsz.backend.domain.dto.DoacoesDto
import com.kropsz.backend.exception.EntityNotFound
import com.kropsz.backend.repository.DoacoesRepository
import com.kropsz.backend.repository.LocaisColetaRepository
import com.kropsz.backend.repository.PessoaRepository
import com.kropsz.backend.service.DoacoesService
import org.springframework.stereotype.Service
import java.time.Instant
import java.time.LocalDate

@Service
class DoacoesServiceImpl(
    val doacoesRepository: DoacoesRepository,
    val pessoaRepository: PessoaRepository,
    val locaisColetaRepository: LocaisColetaRepository
) : DoacoesService {

    override fun adicionarDoacao(doacao : DoacoesDto) : Doacoes {
        val pessoa = pessoaRepository.findById(doacao.pessoaId).get()
        val local = locaisColetaRepository.findById(doacao.localId)
            .orElseThrow { throw EntityNotFound("Local de coleta não encontrado") }
        val novaDoacao = Doacoes(
            local = local,
            data = LocalDate.parse(doacao.dataDoacao),
            createdAt = Instant.now(),
            updatedAt = Instant.now()
        )
        novaDoacao.pessoas = setOf(pessoa)

        return doacoesRepository.save(novaDoacao)
    }

    override fun listarDoacoes() : List<Doacoes> {
        return doacoesRepository.findAll()
    }

    override fun buscarDoacao(id : Int) : Doacoes {
        return doacoesRepository.findById(id).get()
    }

    override fun atualizarDoacao(id: Int, doacao : DoacoesDto) : Doacoes {
        val doacaoAtual = doacoesRepository.findById(id)
            .orElseThrow { throw EntityNotFound("Doação não encontrada") }

        val local = locaisColetaRepository.findById(doacao.localId)
            .orElseThrow { throw EntityNotFound("Local de coleta não encontrado") }

        doacaoAtual.local = local
        doacaoAtual.data = LocalDate.parse(doacao.dataDoacao)
        doacaoAtual.updatedAt = Instant.now()
        return doacoesRepository.save(doacaoAtual)
    }

    override fun deletarDoacao(id : Int) {
        doacoesRepository.deleteById(id)
    }
}