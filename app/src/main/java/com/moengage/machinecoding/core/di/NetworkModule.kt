package com.moengage.machinecoding.core.di

import android.content.Context
import com.moengage.machinecoding.core.data.dataSource.DefaultOnBoardingDataSource
import com.moengage.machinecoding.core.data.dataSource.OnBoardingDataSource
import com.moengage.machinecoding.core.data.networkHelper.DefaultNetworkHelper
import com.moengage.machinecoding.core.data.networkHelper.OnBoardingNetworkHelper
import com.moengage.machinecoding.core.network.OnBoardingNetworkManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesDefaultOnBoardingNetworkHelper(
        @ApplicationContext applicationContext: Context
    ) : OnBoardingNetworkHelper{
        return DefaultNetworkHelper(applicationContext)
    }

    @Provides
    @Singleton
    fun providesDefaultOnBoardinDataSource(
        onBoardingNetworkHelper: OnBoardingNetworkHelper
    ) : OnBoardingDataSource{
        return DefaultOnBoardingDataSource(onBoardingNetworkHelper)
    }
}