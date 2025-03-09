package com.example.musicplayerdemo2.di

import android.content.Context
import com.example.musicplayerdemo2.contentProvider.MusicContentResolver
import com.example.musicplayerdemo2.model.DataStorePrefUtils
import com.example.musicplayerdemo2.model.Media3Components
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MusicAppModule {

    @Provides
    fun providesMusicContentResolver(@ApplicationContext context: Context): MusicContentResolver{
        return MusicContentResolver(context)
    }

    @Provides
    @Singleton
    fun providesMedia3Components(@ApplicationContext context: Context): Media3Components{
        return Media3Components(context)
    }


    @Provides
    @Singleton
    fun providesDataStorePrefUtils(@ApplicationContext context: Context): DataStorePrefUtils{
        return DataStorePrefUtils(context)
    }


}