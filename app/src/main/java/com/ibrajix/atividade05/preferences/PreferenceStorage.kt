package com.atividades.atividade05.preferences

import kotlinx.coroutines.flow.Flow

interface PreferenceStorage {

    //verifica se o usuário salvou alguns detalhes no banco de dados, move para a tela de detalhes se salvo
    //defina a ordem de classificação de todas as músicas
    fun savedKey() : Flow<Boolean>
    suspend fun setSavedKey(order: Boolean)

}