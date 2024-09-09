package com.kropsz.backend.repository

import com.kropsz.backend.domain.Doacoes
import org.springframework.data.jpa.repository.JpaRepository

interface DoacoesRepository : JpaRepository<Doacoes, Int> {
}