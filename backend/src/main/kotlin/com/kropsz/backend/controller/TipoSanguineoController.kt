package com.kropsz.backend.controller

import com.kropsz.backend.domain.TipoSanguineo
import com.kropsz.backend.domain.dto.TipoSanguineoDto
import com.kropsz.backend.service.TipoSanguineoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/tipos-sanguineos")
class TipoSanguineoController(val tipoSanguineoService: TipoSanguineoService) {

    @GetMapping("/{id}")
    fun buscarTipoSanguineo(@PathVariable id: Int): ResponseEntity<TipoSanguineo> {
        val tipoSanguineo = tipoSanguineoService.buscarTipoSanguineo(id)
        return ResponseEntity.ok(tipoSanguineo)
    }

    @GetMapping("/fator/{fator}")
    fun buscarTipoSanguineoPeloFator(@PathVariable fator: String): ResponseEntity<TipoSanguineo> {
        val tipoSanguineo = tipoSanguineoService.buscarTipoSanguineoPeloFator(fator)
        return ResponseEntity.ok(tipoSanguineo)
    }

    @GetMapping
    fun buscarTodosTiposSanguineos(): ResponseEntity<List<TipoSanguineo>> {
        val tiposSanguineos = tipoSanguineoService.buscarTodosTiposSanguineos()
        return ResponseEntity.ok(tiposSanguineos)
    }

    @PostMapping
    fun adicionarTipoSanguineo(@RequestBody tipoSanguineoDto: TipoSanguineoDto): ResponseEntity<TipoSanguineo> {
        val novoTipoSanguineo = tipoSanguineoService.adicionarTipoSanguineo(tipoSanguineoDto)
        return ResponseEntity.created(
            URI("/tipos-sanguineos/${novoTipoSanguineo.id}")
        ).body(novoTipoSanguineo)
    }

    @PutMapping("/{id}")
    fun atualizarTipoSanguineo(@PathVariable id: Int, @RequestBody tipoSanguineoDto: TipoSanguineoDto): ResponseEntity<TipoSanguineo> {
        val tipoSanguineoAtualizado = tipoSanguineoService.atualizarTipoSanguineo(id, tipoSanguineoDto)
        return ResponseEntity.ok(tipoSanguineoAtualizado)
    }

    @DeleteMapping("/{id}")
    fun deletarTipoSanguineo(@PathVariable id: Int): ResponseEntity<Void> {
        tipoSanguineoService.deletarTipoSanguineo(id)
        return ResponseEntity.noContent().build()
    }
}