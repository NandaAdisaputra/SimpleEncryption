package com.nandaadisaputra.simpleencryption.injection

import android.content.Context
import com.nandaadisaputra.simpleencryption.data.Session
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DataModule {
    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context) = Session(context)
}