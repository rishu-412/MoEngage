package com.moengage.machinecoding.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moengage.machinecoding.core.data.repository.OnBoardingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val onBoardingRepository: OnBoardingRepository) : ViewModel() {
    fun getOnBoardingQuestions(){
        viewModelScope.launch {
            onBoardingRepository.getOnBoardingQuestions()
        }
    }
}