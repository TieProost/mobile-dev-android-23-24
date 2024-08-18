package com.tieproost.fitnessapp.fake.repository

import com.tieproost.fitnessapp.data.repository.SettingsRepository
import com.tieproost.fitnessapp.fake.FakeDataSource
import com.tieproost.fitnessapp.model.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSettingsRepository : SettingsRepository {
    override fun getSettings(): Flow<Settings> =
        flow {
            emit(FakeDataSource.settings)
        }

    override suspend fun save(settings: Settings) {
    }
}
