package com.moengage.machinecoding.core.data.networkHelper

import android.content.Context
import android.util.Log
import com.moengage.machinecoding.core.model.QuestionModel
import com.moengage.machinecoding.core.network.OnBoardingNetworkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.ByteArrayInputStream
import java.io.ObjectInputStream

class DefaultNetworkHelper constructor(
    private val applicationContext : Context) : OnBoardingNetworkHelper {

    override suspend fun getOnBoardingQuestions(): List<QuestionModel> {

        val inputStream = OnBoardingNetworkManager.getOnboardingQuestions(applicationContext)
        if(inputStream == null)
        {
            Log.i("DefaultNetworkHelper", "getOnBoardingQuestions: input stream is null")
            return emptyList()
        }

        val byteArrayInputStream = ByteArrayInputStream(inputStream)
        val objectInputStream = withContext(Dispatchers.IO) {
            ObjectInputStream(byteArrayInputStream)
        }
        withContext(Dispatchers.IO) {
            Log.i("TAG", "getOnBoardingQuestions: ${objectInputStream.readObject()}")
        }
        return listOf()

    }
}