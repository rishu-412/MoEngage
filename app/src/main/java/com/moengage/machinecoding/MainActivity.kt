package com.moengage.machinecoding

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.moengage.machinecoding.core.MainActivityViewModel
import com.moengage.machinecoding.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var nullableViewBinding : ActivityMainBinding? = null
    private val viewBinding : ActivityMainBinding get() = nullableViewBinding!!
    private lateinit var mainActivityViewMode : MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewMode = ViewModelProvider(this)[MainActivityViewModel::class.java]
        nullableViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        mainActivityViewMode.getOnBoardingQuestions()
    }
}