package com.example.musicplayerdemo2.di

import android.content.Context
import com.example.musicplayerdemo2.contentProvider.MusicContentResolver
import com.example.musicplayerdemo2.model.Media3Components
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object MusicAppModule {

    @Provides
    fun providesMusicContentResolver(@ApplicationContext context: Context): MusicContentResolver{
        return MusicContentResolver(context)
    }

    @Provides
    fun providesMedia3Components(@ApplicationContext context: Context): Media3Components{
        return Media3Components(context)
    }

}