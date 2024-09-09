package com.kropsz.backend.repository

import com.kropsz.backend.domain.Pessoa
import org.springframework.data.jpa.repository.JpaRepository

interface PessoaRepository : JpaRepository<Pessoa, Int> {

    fun findByNome(nome: String): List<Pessoa>
}