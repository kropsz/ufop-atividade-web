package com.kropsz.backend.service

import com.kropsz.backend.domain.TipoSanguineo
import com.kropsz.backend.domain.dto.TipoSanguineoDto

interface TipoSanguineoService {

    fun buscarTipoSanguineo(id: Int): TipoSanguineo

    fun buscarTipoSanguineoPeloFator(fator: String): TipoSanguineo

    fun buscarTodosTiposSanguineos(): List<TipoSanguineo>

    fun adicionarTipoSanguineo(tipoSanguineo: TipoSanguineoDto): TipoSanguineo

    fun atualizarTipoSanguineo(id: Int, tipoSanguineo: TipoSanguineoDto): TipoSanguineo

    fun deletarTipoSanguineo(id: Int)
}