package com.moengage.machinecoding.core.data.dataSource

import com.moengage.machinecoding.core.model.QuestionModel

interface OnBoardingDataSource {


    suspend fun onBoardingQuestions() : List<QuestionModel>
}