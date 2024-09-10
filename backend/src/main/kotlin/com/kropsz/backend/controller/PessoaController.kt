package com.kropsz.backend.controller

import com.kropsz.backend.domain.Pessoa
import com.kropsz.backend.domain.dto.PessoaDto
import com.kropsz.backend.service.PessoaService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/pessoa")
class PessoaController(val pessoaService: PessoaService) {

    @GetMapping
    fun findAll(): ResponseEntity<List<Pessoa>> {
        return ResponseEntity.ok(pessoaService.listarPessoas())
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Int): ResponseEntity<Pessoa> {
        return ResponseEntity.ok(pessoaService.buscarPessoaPorId(id))
    }

    @PostMapping
    fun save(@RequestBody pessoa: PessoaDto): ResponseEntity<Pessoa> {
        return ResponseEntity.created(
            URI.create("/pessoa/${pessoaService.salvarPessoa(pessoa).id}")
        ).build(
        )
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Int, @RequestBody pessoa: PessoaDto): ResponseEntity<Pessoa> {
        return ResponseEntity.ok(pessoaService.atualizarPessoa(id, pessoa))
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Void> {
        pessoaService.deletarPessoa(id)
        return ResponseEntity.noContent().build()
    }
}