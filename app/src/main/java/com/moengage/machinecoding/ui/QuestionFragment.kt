package com.moengage.machinecoding.ui

import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.moengage.machinecoding.MainActivity
import com.moengage.machinecoding.core.MainActivityViewModel
import com.moengage.machinecoding.core.model.QuestionModel
import com.moengage.machinecoding.databinding.ActivityMainBinding
import com.moengage.machinecoding.databinding.FragmentQuestionBinding

class QuestionFragment : Fragment() {

    private var nullableViewBinding : FragmentQuestionBinding? = null
    private val viewBinding : FragmentQuestionBinding get() = nullableViewBinding!!
    private lateinit var textQuestion :String
    private lateinit var inputType :String
    private var isLast : Boolean = false
    companion object{
        private final const val KEY_QUESTION = "k-question"
        private final const val KEY_TYPE= "k-type"
        private final const val KEY_IS_LAST= "k-is-last"
        fun newInstance(question: String, type: String, isLast : Boolean): QuestionFragment{
            val args = Bundle()
            args.putString(KEY_QUESTION, question)
            args.putString(KEY_TYPE,  type)
            args.putBoolean(KEY_IS_LAST, isLast)
            val fragment = QuestionFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val args = requireArguments()
        textQuestion = args.getString(KEY_QUESTION)?:throw Exception()
        inputType = args.getString(KEY_TYPE)?:throw Exception()
        isLast = args.getBoolean(KEY_IS_LAST)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        nullableViewBinding = FragmentQuestionBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding){
            question.text = textQuestion
            when (inputType) {
                "string" -> answer.inputType = InputType.TYPE_CLASS_TEXT
                "integer" -> answer.inputType = InputType.TYPE_CLASS_NUMBER
                "email" -> answer.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                else -> answer.inputType = InputType.TYPE_CLASS_TEXT
            }
            answer.requestFocus()
            if(isLast) next.text = "Finish" else "Next"

            previous.setOnClickListener{
                if(requireActivity() is MainActivity){
                    (requireActivity() as MainActivity).handlePrevious()
                }
            }

            next.setOnClickListener{
                (requireActivity() as MainActivity).handleNext(viewBinding.answer.text.toString())
            }
        }
    }



}