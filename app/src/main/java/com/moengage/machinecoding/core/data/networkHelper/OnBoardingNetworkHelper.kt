package com.moengage.machinecoding.core.data.networkHelper

import com.moengage.machinecoding.core.model.QuestionModel

interface OnBoardingNetworkHelper {


    suspend fun getOnBoardingQuestions() : List<QuestionModel>
}