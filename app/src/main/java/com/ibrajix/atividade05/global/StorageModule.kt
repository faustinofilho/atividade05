package com.atividades.atividade05.global

import com.atividades.atividade05.preferences.PreferenceImpl
import com.atividades.atividade05.preferences.PreferenceStorage
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {

    @Binds
    abstract fun bindsPreferenceStorage(preferenceStorageImpl: PreferenceImpl): PreferenceStorage

}