package com.example.quizquizalis.presentation.fragments.home

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.quizquizalis.core.base.BaseFragment
import com.example.quizquizalis.R
import com.example.quizquizalis.domain.model.QuizModell
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.quizquizalis.databinding.FragmentHomeBinding
import com.example.quizquizalis.ext.visibility
import com.example.quizquizalis.core.network.Status
import com.example.quizquizalis.utils.Constants
import com.google.gson.Gson

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private var amount: String = "10"
    private var categoryNum: String = "All"
    private var diffPosition: String = "All"
    private var categoryName: String = "All"
    private lateinit var options1 :ArrayList<String>
    private lateinit var options2 :ArrayList<String>
    private val viewModel:HomeViewModel by viewModel()

    override fun bind(): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(layoutInflater)
    }
    override fun initView() {
        options1 = resources.getStringArray(R.array.category).toList() as ArrayList<String>
        options2 = resources.getStringArray(R.array.difficulty).toList() as ArrayList<String>

        binding.spinner1.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, options1)
        binding.spinner2.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, options2)
    }
    override fun setupListener() {
        binding.spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                categoryName = options1[position]

                 if (position != 0) categoryNum = (8 + position).toString()
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {
                categoryNum = options1[0]
            }
        }

        binding.spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                diffPosition = options2[position]
    }
            override fun onNothingSelected(parent: AdapterView<*>?) {
                diffPosition = options2[0]

            }
        }
        binding.mySeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                binding.tvAmount.text = progress.toString()
                amount = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                amount = seekBar?.progress.toString()
            }
        })

        binding.btnStart.setOnClickListener {
            getData()
        }
        viewModel.loadingProgressBar.observe(viewLifecycleOwner,{
            binding.progressBar.visibility(it)
        })



    }

    private fun getData() {
        viewModel.getQuestions(amount,categoryNum,diffPosition).observe(viewLifecycleOwner,{
            when(it.status){
                Status.LOADING->{
                    viewModel.loadingProgressBar.postValue(true)
                }
                Status.SUCCESS->{
                    viewModel.loadingProgressBar.postValue(false)
                    openQuizFragment(it.data)
                }
                Status.ERROR->{
                    viewModel.loadingProgressBar.postValue(false)
                    Toast.makeText(requireContext(),"Something wrong...", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun openQuizFragment(data: QuizModell?) {
        val bundle = Bundle()
        bundle.putString(Constants.DATA_KEY, Gson().toJson(data))
        bundle.putString(Constants.CATEGORY_KEY,categoryName)
        bundle.putString(Constants.DIFFICULTY_KEY,diffPosition)
        findNavController().popBackStack(R.id.homeFragment,true)
        findNavController().navigate(R.id.quizFragment,bundle)
    }


}