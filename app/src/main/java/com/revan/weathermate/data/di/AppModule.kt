package com.revan.weathermate.data.di

import android.content.Context
import android.content.SharedPreferences
import com.revan.weathermate.data.remote.WeatherForecastAPI
import com.revan.weathermate.data.repository.WeatherForecastRepositoryImpl
import com.revan.weathermate.domain.repository.WeatherForecastRepository
import com.revan.weathermate.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideWeatherForecastAPI () : WeatherForecastAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(WeatherForecastAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherForecastRepository (api : WeatherForecastAPI) : WeatherForecastRepository {
        return WeatherForecastRepositoryImpl(api)
    }

}