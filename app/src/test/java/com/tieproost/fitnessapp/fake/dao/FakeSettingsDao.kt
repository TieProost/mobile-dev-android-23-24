package com.tieproost.fitnessapp.fake.dao

import com.tieproost.fitnessapp.data.database.dao.SettingsDao
import com.tieproost.fitnessapp.data.database.model.DbSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeSettingsDao(
    initialSettings: DbSettings? = DbSettings(),
) : SettingsDao {
    private var _settings: DbSettings? = null

    var settings: DbSettings?
        get() = _settings
        set(newSettings) {
            _settings = newSettings
        }

    init {
        settings = initialSettings
    }

    override suspend fun insert(item: DbSettings) {
    }

    override suspend fun update(item: DbSettings) {
        settings = item
    }

    override fun get(): Flow<DbSettings?> =
        flow {
            emit(settings ?: DbSettings())
        }
}
