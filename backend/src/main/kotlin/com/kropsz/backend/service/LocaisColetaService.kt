package com.kropsz.backend.service

import com.kropsz.backend.domain.LocaisColeta
import com.kropsz.backend.domain.dto.LocaisColetaDto

interface LocaisColetaService {

    fun buscarLocaisColeta(): List<LocaisColeta>

    fun buscarLocalColeta(id: Int): LocaisColeta

    fun adicionarLocalColeta(localColeta: LocaisColetaDto): LocaisColeta

    fun deletarLocalColeta(id: Int)

    fun atualizarLocalColeta(id: Int, localColeta: LocaisColetaDto): LocaisColeta
}