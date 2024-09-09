package com.kropsz.backend.repository

import com.kropsz.backend.domain.TipoSanguineo
import org.springframework.data.jpa.repository.JpaRepository

interface TipoSanguineoRepository : JpaRepository<TipoSanguineo, Int> {
}