package com.kropsz.backend.controller

import com.kropsz.backend.domain.Cidade
import com.kropsz.backend.domain.dto.CidadeDto
import com.kropsz.backend.service.CidadeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/cidades")
class CidadeController (val cidadeService: CidadeService) {

    @GetMapping
    fun listar(): ResponseEntity<List<Cidade>> {
        return ResponseEntity.ok(cidadeService.listarCidades())
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Int): ResponseEntity<Cidade> {
        return ResponseEntity.ok(cidadeService.buscarCidadePorId(id))
    }

    @GetMapping("/nome/{nome}")
    fun buscarPorNome(@PathVariable nome: String):ResponseEntity<Cidade> {
        return ResponseEntity.ok(cidadeService.buscarCidadePorNome(nome))
    }

    @PostMapping
    fun criar(@RequestBody cidade: CidadeDto): ResponseEntity<Cidade> {
        val cidadeSalva = cidadeService.salvarCidade(cidade)
        return ResponseEntity.created(
            URI.create("/cidades/${cidadeSalva.id}")
        ).body(cidadeSalva)
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Int, @RequestBody cidade: CidadeDto): ResponseEntity<Cidade> {
        return ResponseEntity.ok(cidadeService.atualizarCidade(id, cidade))
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Int): ResponseEntity<Void> {
        cidadeService.deletarCidade(id)
        return ResponseEntity.noContent().build()
    }
}