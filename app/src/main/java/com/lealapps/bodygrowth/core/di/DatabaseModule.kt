package com.lealapps.bodygrowth.core.di

import android.app.Application
import androidx.room.Room
import com.lealapps.bodygrowth.core.data.TrainingDatabase
import com.lealapps.bodygrowth.core.domain.repository.TrainingRepository
import com.lealapps.bodygrowth.core.data.TrainingRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideRoomDatabase(app: Application): TrainingDatabase =
        Room.databaseBuilder(
            context = app,
            klass = TrainingDatabase::class.java,
            name = "training_db"
        ).build()

    @Provides
    @Singleton
    fun provideTrainingRepository(trainingDatabase: TrainingDatabase): TrainingRepository {
        return TrainingRepositoryImpl(trainingDatabase.dao())
    }

}