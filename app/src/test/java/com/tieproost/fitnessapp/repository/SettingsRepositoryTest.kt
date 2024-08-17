package com.tieproost.fitnessapp.repository

import com.tieproost.fitnessapp.data.database.model.asDbSettings
import com.tieproost.fitnessapp.data.repository.CachingSettingsRepository
import com.tieproost.fitnessapp.fake.FakeDataSource
import com.tieproost.fitnessapp.fake.dao.FakeSettingsDao
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class SettingsRepositoryTest {
    private lateinit var repository: CachingSettingsRepository

    @Before
    fun initializeRepository() {
        repository =
            CachingSettingsRepository(
                FakeSettingsDao(FakeDataSource.settings.asDbSettings()),
            )
    }

    @Test
    fun `getSettings returns settings row`() =
        runTest {
            assertEquals(
                FakeDataSource.settings,
                repository.getSettings().first(),
            )
        }

    @Test
    fun `save updates Settings`() =
        runTest {
            repository.save(FakeDataSource.newSettings)

            assertEquals(
                FakeDataSource.newSettings,
                repository.getSettings().first(),
            )
        }
}
