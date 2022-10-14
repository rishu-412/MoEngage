package com.moengage.machinecoding.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuestionModel(
    val question : String,
    val inputType : String,
    var answer : String?="Not Answered"
) : java.io.Serializable,Parcelable