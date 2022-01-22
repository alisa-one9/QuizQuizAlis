package com.example.quizquizalis.presentation.fragments.results

import androidx.navigation.fragment.findNavController
import com.example.quizquizalis.core.base.BaseFragment
import com.example.quizquizalis.R

import com.example.quizquizalis.data.local.RoomResultModel
import com.example.quizquizalis.databinding.FragmentResultBinding
import com.example.quizquizalis.ext.visibility

import com.example.quizquizalis.utils.Constants
import com.google.gson.Gson

class ResultFragment: BaseFragment<FragmentResultBinding>() {

    lateinit var history: RoomResultModel


    override fun bind(): FragmentResultBinding {
       return  FragmentResultBinding.inflate(layoutInflater)
    }

    override fun setupListener() {
        binding.btnFinish.setOnClickListener {
            findNavController().popBackStack(R.id.resultFragment, true)
            findNavController().navigate(R.id.homeFragment)
        }
    }

    override fun initView() {
        if (arguments != null)
            history = Gson().fromJson(
                requireArguments().getString(Constants.RESULT_KEY),
                RoomResultModel::class.java
            )
        setData()
    }

    private fun setData() {
        val amount = history.amount?.toInt()
        val score = (history.correctAnswers * 100) / amount!!

        if (score > 50) binding.ivMark.visibility(true)

        binding.tvMixed.text = resources.getString(R.string.category_mixed,history.category)
        binding.tvDifficultyResult.text = history.difficulty
        val correctAns = history.correctAnswers
        "$correctAns/$amount".also { binding.tvCorrectAnswerResult.text = it }
        "$score%".also { binding.tvResultMixed.text = it }
    }
}