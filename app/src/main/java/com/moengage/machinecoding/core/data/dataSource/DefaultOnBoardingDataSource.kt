package com.moengage.machinecoding.core.data.dataSource

import com.moengage.machinecoding.core.data.networkHelper.OnBoardingNetworkHelper
import com.moengage.machinecoding.core.model.QuestionModel

class DefaultOnBoardingDataSource(
    private val onBoardingNetworkHelper: OnBoardingNetworkHelper
) : OnBoardingDataSource {
    override suspend fun onBoardingQuestions(): List<QuestionModel> {
        return onBoardingNetworkHelper.getOnBoardingQuestions()

    }

}