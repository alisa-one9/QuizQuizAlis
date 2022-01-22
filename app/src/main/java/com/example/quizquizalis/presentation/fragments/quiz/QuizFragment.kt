package com.example.quizquizalis.presentation.fragments.quiz

import android.os.Bundle
import android.os.Handler
import android.os.Looper

import androidx.navigation.fragment.findNavController
import com.example.quizquizalis.core.base.BaseFragment
import com.example.quizquizalis.R
import com.example.quizquizalis.data.local.RoomResultModel

import com.example.quizquizalis.domain.model.QuizModell
import com.example.quizquizalis.domain.model.Resultt
import org.koin.androidx.viewmodel.ext.android.viewModel

import com.example.quizquizalis.databinding.FragmentQuizzquizzBinding
import com.example.quizquizalis.ext.loadImage
import com.example.quizquizalis.ext.visibility
import com.example.quizquizalis.utils.Constants

import com.google.gson.Gson
import java.util.ArrayList

class QuizFragment : BaseFragment<FragmentQuizzquizzBinding>(){


         private var results: List<Resultt>? = null
         private lateinit var history: RoomResultModel
         private var pos: Int = 0
         private lateinit var corrAns: String
         private var size = 0
         private val viewModel: QuizViewModel by viewModel()

 override fun bind(): FragmentQuizzquizzBinding{
     return  FragmentQuizzquizzBinding.inflate(layoutInflater)
 }

    override fun setupListener() {
        binding.btn1.setOnClickListener { checkData(corrAns==binding.btn1.text)}
        binding.btn2.setOnClickListener { checkData(corrAns==binding.btn1.text)}
        binding.btn3.setOnClickListener { checkData(corrAns==binding.btn1.text)}
        binding.btn4.setOnClickListener { checkData(corrAns==binding.btn1.text)}

        binding.btn1True.setOnClickListener { checkData(corrAns==binding.btn1True.text) }
        binding.btn2False.setOnClickListener { checkData(corrAns==binding.btn1True.text) }
        binding.btnSkip.setOnClickListener { checkData(false) }

        binding.ivBack.setOnClickListener{
            findNavController().navigate(R.id.homeFragment)
        }
    }

        private fun checkData(isCorrect: Boolean) {
            if (isCorrect) {
                viewModel.setCorrectAns()
            }
            binding.ivIsCorrect.visibility(true)
            binding.ivIsCorrect.loadImage(isCorrect)

            Handler(Looper.getMainLooper()).postDelayed({
                startGame()
            }, 500)
        }

    override fun initView() {
        if (arguments != null) {
            results = Gson().fromJson(
                requireArguments().getString(Constants.DATA_KEY),
                QuizModell::class.java
            ).results

            history = RoomResultModel(
                0,
                requireArguments().getString(Constants.CATEGORY_KEY),
                0,
                requireArguments().getString(Constants.DIFFICULTY_KEY),
                results?.size.toString(),
                System.currentTimeMillis()
            )
            startGame()
        }
    }
    private fun startGame() {
        pos = viewModel.getPosition()
        size = results?.size!!

        if (size > pos) {
            setData(pos)
        } else {
            saveData()
        }
    }
    private fun setData(pos: Int) {
        val result = results?.get(pos)
        corrAns = checkText(result?.correct_answer.toString())

        binding.ivIsCorrect.visibility(false)
        binding.tvQuestion.text = checkText(result?.question)
        binding.tvHeaderCategory.text = result?.category
        binding.progressIndicator.max = size
        val position = pos + 1
        binding.progressIndicator.progress = position

        "$position/$size".also { binding.tvProgress.text = it }

        if (result?.type == "multiple") {
            binding.multipleLayout.visibility(true)
            binding.trueFalseLayout.visibility(false)

            val answers = (result.incorrect_answers as ArrayList<String>?)!!
            answers.add(result.correct_answer.toString())
            answers.shuffle()

            binding.btn1.text = checkText(answers[0])
            binding.btn2.text = checkText(answers[1])
            binding.btn3.text = checkText(answers[2])
            binding.btn4.text = checkText(answers[3])

        } else {
            binding.multipleLayout.visibility(false)
            binding.trueFalseLayout.visibility(true)
        }
    }
    private fun saveData() {
        history.correctAnswers = viewModel.getCorrectAns()
        viewModel.insert(history)
        openResultFragment()
    }
    private fun openResultFragment() {
        val bundle = Bundle()
        bundle.putString(Constants.RESULT_KEY, Gson().toJson(history))

        findNavController().popBackStack(R.id.quizFragment, true)
        findNavController().navigate(R.id.resultFragment,bundle)
    }
    private fun checkText(text: String?): String {
        var newText: String = text?.replace("&quot;", "\"").toString()
        newText = newText.replace("&#039;", "'")

        return newText
    }
}

