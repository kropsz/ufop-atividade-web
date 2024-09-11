package com.kropsz.backend.service

import com.kropsz.backend.domain.Estado
import com.kropsz.backend.domain.dto.EstadoDto

interface EstadoService {

    fun listarEstados(): List<Estado>

    fun buscarEstado(id: Int): Estado

    fun adicionarEstado(estado: EstadoDto): Estado

    fun ataualizarEstado(id: Int, estado: EstadoDto): Estado

    fun deletarEstado(id: Int): Estado
}