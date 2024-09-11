package com.kropsz.backend.controller

import com.kropsz.backend.domain.Estado
import com.kropsz.backend.domain.dto.EstadoDto
import com.kropsz.backend.service.EstadoService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/estados")
class EstadosController (val estadosService: EstadoService) {

    @GetMapping
    fun getEstados(): ResponseEntity<List<Estado>> {
        return ResponseEntity.ok(estadosService.listarEstados())
    }

    @GetMapping("/{id}")
    fun getEstado(@PathVariable id: Int): ResponseEntity<Estado> {
        return ResponseEntity.ok(estadosService.buscarEstado(id))
    }

    @PostMapping
    fun addEstado(@RequestBody estado: EstadoDto): ResponseEntity<Estado> {
        val entity = estadosService.adicionarEstado(estado)
        return ResponseEntity.created(
            URI.create("/estados/${entity.id}")
        ).body(entity)

    }

    @PutMapping("/{id}")
    fun updateEstado(@PathVariable id: Int, @RequestBody estado: EstadoDto): ResponseEntity<Estado> {
        return ResponseEntity.ok(estadosService.ataualizarEstado(id, estado))
    }

    @DeleteMapping("/{id}")
    fun deleteEstado(@PathVariable id: Int): ResponseEntity<Unit> {
        estadosService.deletarEstado(id)
        return ResponseEntity.noContent().build()
    }
}