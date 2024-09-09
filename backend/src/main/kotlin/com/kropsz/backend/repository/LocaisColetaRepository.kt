package com.kropsz.backend.repository

import com.kropsz.backend.domain.LocaisColeta
import org.springframework.data.jpa.repository.JpaRepository

interface LocaisColetaRepository : JpaRepository<LocaisColeta, Int> {
}