package com.moengage.machinecoding

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.moengage.machinecoding.core.model.QuestionModel
import com.moengage.machinecoding.databinding.ActivityFinalBinding
import com.moengage.machinecoding.databinding.ActivityMainBinding
import com.moengage.machinecoding.ui.QuestionListAdapter

class FinalActivity : AppCompatActivity() {
    private var nullableViewBinding : ActivityFinalBinding? = null
    private val viewBinding : ActivityFinalBinding get() = nullableViewBinding!!
    private lateinit var list : List<QuestionModel>
    companion object{
        private const val KEY_LIST = "k-list"
        fun getStartIntent(context : Context, list: List<QuestionModel>) : Intent {
            val arrayList = ArrayList<QuestionModel>(list)
            val intent = Intent(context, FinalActivity::class.java)
            intent.putParcelableArrayListExtra(KEY_LIST, arrayList)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        nullableViewBinding = ActivityFinalBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        list = intent.getParcelableArrayListExtra(KEY_LIST)?: throw Exception()
        viewBinding.recyclerQuestion.adapter = QuestionListAdapter(list)
        viewBinding.recyclerQuestion.layoutManager = LinearLayoutManager(this)
        viewBinding.recyclerQuestion.setHasFixedSize(true)
    }
}