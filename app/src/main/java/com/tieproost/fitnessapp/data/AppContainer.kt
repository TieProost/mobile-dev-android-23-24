package com.tieproost.fitnessapp.data

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.tieproost.fitnessapp.BuildConfig
import com.tieproost.fitnessapp.data.database.RoomDb
import com.tieproost.fitnessapp.data.repository.CachingExerciseRepository
import com.tieproost.fitnessapp.data.repository.CachingMealsRepository
import com.tieproost.fitnessapp.data.repository.CachingSettingsRepository
import com.tieproost.fitnessapp.data.repository.ExerciseRepository
import com.tieproost.fitnessapp.data.repository.MealsRepository
import com.tieproost.fitnessapp.data.repository.SettingsRepository
import com.tieproost.fitnessapp.network.FitnessApiService
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

interface AppContainer {
    val mealsRepository: MealsRepository
    val exerciseRepository: ExerciseRepository
    val settingsRepository: SettingsRepository
}

class DefaultAppContainer(
    private val context: Context,
) : AppContainer {
    private val baseUrl = "https://trackapi.nutritionix.com/v2/"

    private val client =
        OkHttpClient
            .Builder()
            .addNetworkInterceptor(
                Interceptor {
                    val newRequest =
                        it
                            .request()
                            .newBuilder()
                            .addHeader("x-app-id", BuildConfig.X_APP_ID)
                            .addHeader("x-app-key", BuildConfig.X_APP_KEY)
                            .addHeader("x-remote-user-id", "0")
                            .build()

                    it.proceed(newRequest)
                },
            ).build()

    private val json = Json { ignoreUnknownKeys = true }

    private val retrofit =
        Retrofit
            .Builder()
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(baseUrl)
            .client(client)
            .build()

    private val retrofitService: FitnessApiService by lazy {
        retrofit.create(FitnessApiService::class.java)
    }

    override val mealsRepository: MealsRepository by lazy {
        CachingMealsRepository(retrofitService, RoomDb.getDatabase(context).foodDao(), context)
    }

    override val exerciseRepository: ExerciseRepository by lazy {
        CachingExerciseRepository(retrofitService, RoomDb.getDatabase(context).exerciseDao())
    }

    override val settingsRepository: SettingsRepository by lazy {
        CachingSettingsRepository(RoomDb.getDatabase(context).settingsDao())
    }
}
