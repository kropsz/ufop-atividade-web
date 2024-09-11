package com.kropsz.backend.controller

import com.kropsz.backend.domain.LocaisColeta
import com.kropsz.backend.domain.dto.LocaisColetaDto
import com.kropsz.backend.service.LocaisColetaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/locais-coleta")
class LocaisColetaController(val locaisColetaService: LocaisColetaService) {

    @GetMapping
    fun listar(): ResponseEntity<List<LocaisColeta>> {
        return ResponseEntity.ok(locaisColetaService.buscarLocaisColeta())
    }

    @PostMapping
    fun cadastrar(@RequestBody locaisColeta: LocaisColetaDto): ResponseEntity<LocaisColeta> {
        val locaisColetaEntity = locaisColetaService.adicionarLocalColeta(locaisColeta)
        return ResponseEntity.created(
            URI("/locais-coleta/${locaisColetaEntity.id}")
        ).body(locaisColetaEntity)
    }

    @PutMapping("/{id}")
    fun atualizar(@PathVariable id: Int, @RequestBody locaisColeta: LocaisColetaDto): ResponseEntity<LocaisColeta> {
        return ResponseEntity.ok(locaisColetaService.atualizarLocalColeta(id, locaisColeta))
    }

    @DeleteMapping("/{id}")
    fun deletar(@PathVariable id: Int): ResponseEntity<Unit> {
        locaisColetaService.deletarLocalColeta(id)
        return ResponseEntity.noContent().build()
    }


}