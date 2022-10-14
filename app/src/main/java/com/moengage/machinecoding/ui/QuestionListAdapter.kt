package com.moengage.machinecoding.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moengage.machinecoding.core.model.QuestionModel
import com.moengage.machinecoding.databinding.LayoutQuestionItemBinding

class QuestionListAdapter(
    private val list: List<QuestionModel>
) : RecyclerView.Adapter<QuestionListAdapter.ViewHolder>() {
    class ViewHolder(private val viewBinding : LayoutQuestionItemBinding): RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(position: Int,questionModel: QuestionModel){
            with(viewBinding){
                labelQuetion.text = "Question: $position"
                question.text = questionModel.question
                answer.text = questionModel.answer
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder( LayoutQuestionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position, list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}