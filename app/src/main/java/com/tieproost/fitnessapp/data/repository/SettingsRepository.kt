package com.tieproost.fitnessapp.data.repository

import com.tieproost.fitnessapp.data.database.dao.SettingsDao
import com.tieproost.fitnessapp.data.database.model.asDbSettings
import com.tieproost.fitnessapp.data.database.model.asDomainSettings
import com.tieproost.fitnessapp.model.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface SettingsRepository {
    fun getSettings(): Flow<Settings>

    suspend fun save(settings: Settings)
}

class CachingSettingsRepository(
    private val settingsDao: SettingsDao,
) : SettingsRepository {
    override suspend fun save(settings: Settings) {
        settingsDao.update(settings.asDbSettings())
    }

    override fun getSettings(): Flow<Settings> = settingsDao.get().map { it?.asDomainSettings() ?: Settings() }
}
