package com.kropsz.backend.controller

import com.kropsz.backend.domain.Doacoes
import com.kropsz.backend.domain.dto.DoacoesDto
import com.kropsz.backend.service.DoacoesService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI


@RestController
@RequestMapping("/doacoes")
class DoacoesController(val doacoesService: DoacoesService) {

    @GetMapping
    fun listarDoacoes(): ResponseEntity<List<Doacoes>> {
        return ResponseEntity.ok(doacoesService.listarDoacoes())
    }

    @PostMapping
    fun salvarDoacao(@RequestBody doacao: DoacoesDto): ResponseEntity<Doacoes> {
        val doacaoSalva = doacoesService.adicionarDoacao(doacao)
        return ResponseEntity.created(
            URI.create("/doacoes/${doacaoSalva.id}")
        ).body(doacaoSalva)

    }

    @DeleteMapping("/{id}")
    fun deletarDoacao(@PathVariable id: Int): ResponseEntity<Void> {
        doacoesService.deletarDoacao(id)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/{id}")
    fun atualizarDoacao(@PathVariable id: Int, @RequestBody doacao: DoacoesDto): ResponseEntity<Doacoes> {
        return ResponseEntity.ok(doacoesService.atualizarDoacao(id, doacao))
    }

}