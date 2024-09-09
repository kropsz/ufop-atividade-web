package com.kropsz.backend.repository

import com.kropsz.backend.domain.Estado
import org.springframework.data.jpa.repository.JpaRepository

interface EstadoRepository : JpaRepository<Estado, Int> {
}