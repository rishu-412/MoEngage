package com.moengage.machinecoding.core.data.networkHelper

import android.content.Context
import android.util.Log
import com.moengage.machinecoding.core.model.QuestionModel
import com.moengage.machinecoding.core.network.OnBoardingNetworkManager
import org.json.JSONObject


class DefaultNetworkHelper constructor(
    private val applicationContext : Context) : OnBoardingNetworkHelper {

    override suspend fun getOnBoardingQuestions(): List<QuestionModel> {

        val inputStream = OnBoardingNetworkManager.getOnboardingQuestions(applicationContext)
        if(inputStream == null)
        {
            Log.i("DefaultNetworkHelper", "getOnBoardingQuestions: input stream is null")
            return emptyList()
        }
        val list = mutableListOf<QuestionModel>()
        val json = String(inputStream)
        val jsonObject = JSONObject(json)
        val jsonArray = jsonObject.getJSONArray("questions")
        for(i in 0 until jsonArray.length()){
            val itemObject = jsonArray.getJSONObject(i)
            val questionModel = QuestionModel(
                itemObject.getString("text"),
                itemObject.getString("type")
            )
            Log.i("TAG", "getOnBoardingQuestions: $questionModel")
            list.add(questionModel)
        }
        return list
    }
}