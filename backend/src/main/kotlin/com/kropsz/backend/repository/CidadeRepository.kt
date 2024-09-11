package com.kropsz.backend.repository

import com.kropsz.backend.domain.Cidade
import org.springframework.data.jpa.repository.JpaRepository

interface CidadeRepository : JpaRepository<Cidade, Int> {

    fun findByNome(nome: String): Cidade
}