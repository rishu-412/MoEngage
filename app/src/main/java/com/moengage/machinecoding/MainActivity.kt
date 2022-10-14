package com.moengage.machinecoding

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.moengage.machinecoding.core.MainActivityViewModel
import com.moengage.machinecoding.core.model.QuestionModel
import com.moengage.machinecoding.databinding.ActivityMainBinding
import com.moengage.machinecoding.ui.QuestionFragment
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlin.math.max

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var nullableViewBinding : ActivityMainBinding? = null
    private val viewBinding : ActivityMainBinding get() = nullableViewBinding!!
    private lateinit var mainActivityViewMode : MainActivityViewModel
    private val listOfQuestion : MutableList<QuestionModel> by lazy { mutableListOf() }
    private var currentPage : Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityViewMode = ViewModelProvider(this)[MainActivityViewModel::class.java]
        nullableViewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        viewBinding.progress.visibility = View.VISIBLE
        mainActivityViewMode.getOnBoardingQuestions().observe(this){
            viewBinding.progress.visibility = View.GONE
            listOfQuestion.addAll(it)
            viewBinding.viewPager.adapter = ViewPagerAdapter(this)
            viewBinding.viewPager.currentItem = currentPage
        }
        viewBinding.viewPager.isUserInputEnabled = false
        viewBinding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                currentPage = position
            }
        })
    }

    fun handlePrevious(){
        viewBinding.viewPager.currentItem = maxOf(currentPage-1, 0)
    }
    fun handleNext(answer : String){
        listOfQuestion[currentPage].answer = answer
        if(currentPage == listOfQuestion.size-1){
            startActivity(FinalActivity.getStartIntent(this, listOfQuestion))
            finish()
            return
        }
        viewBinding.viewPager.currentItem = minOf(currentPage+1, listOfQuestion.size-1)
    }

    inner class ViewPagerAdapter(activity : FragmentActivity): FragmentStateAdapter(activity){
        override fun getItemCount(): Int {
            return listOfQuestion.size
        }

        override fun createFragment(position: Int): Fragment {
            return QuestionFragment.newInstance(listOfQuestion[position].question, listOfQuestion[position].inputType, position == listOfQuestion.size-1)
        }

    }
}