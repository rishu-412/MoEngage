package com.moengage.machinecoding.core.data.repository

import com.moengage.machinecoding.core.data.dataSource.OnBoardingDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OnBoardingRepository @Inject constructor(
    private val onBoardingDataSource: OnBoardingDataSource
) {

    suspend fun getOnBoardingQuestions() = withContext(Dispatchers.IO){
        onBoardingDataSource.onBoardingQuestions()
    }
}