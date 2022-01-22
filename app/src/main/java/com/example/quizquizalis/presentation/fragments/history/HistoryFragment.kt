package com.example.quizquizalis.presentation.fragments.history

import android.util.Log
import com.example.quizquizalis.core.base.BaseFragment
import com.example.quizquizalis.data.local.RoomResultModel
import com.example.quizquizalis.databinding.FragmentHistoryBinding
import com.example.quizquizalis.ext.visibility
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.collections.ArrayList

class HistoryFragment : BaseFragment<FragmentHistoryBinding>() {

    private val viewModel: HistoryViewModel by viewModel()

    override fun bind(): FragmentHistoryBinding {
        return FragmentHistoryBinding.inflate(layoutInflater)
    }

    override fun initView() {
        Log.e("TAG", "initView: ")
        viewModel.getAllHistories()
    }

    override fun setupListener() {

        viewModel.result.observe(this) {
            Log.e("TAG", "setupListener: $it", )
            if (it.isNotEmpty())
                setLocalData(it)
            else showMessage()
        }
    }

    private fun showMessage() {
        Log.e("TAG", "showMessage: ", )
        binding.recyclerviewHistory.visibility(false)
        binding.graph.visibility(false)
        binding.message.visibility(true)
    }

    private fun setLocalData(it: List<RoomResultModel>) {
        binding.recyclerviewHistory.visibility(true)
        binding.graph.visibility(true)
        binding.message.visibility(false)
        Log.e("TAG", "$it ")
        val adapter = HistoryAdapter(it as ArrayList<RoomResultModel>, this::onItemDelete)
        binding.recyclerviewHistory.adapter = adapter
        graph(it)
    }

    private fun onItemDelete(history: RoomResultModel) {
        viewModel.delete(history)
    }

    private fun graph(historyModels: List<RoomResultModel>) {
        binding.graph.removeAllSeries()
        val dataPoints = arrayOfNulls<DataPoint>(historyModels.size)
        for (i in historyModels.indices) dataPoints[i] =
            DataPoint(i.toDouble(), historyModels[i].correctAnswers.toDouble())
        val series = LineGraphSeries(dataPoints)
        binding.graph.addSeries(series)
        binding.graph.viewport.isYAxisBoundsManual = true
        binding.graph.viewport.setMaxY(20.0)
        binding.graph.viewport.isXAxisBoundsManual = true
        binding.graph.viewport.setMaxX(historyModels.size.toDouble())

        // enable scaling and scrolling
        binding.graph.viewport.isScalable = true
        binding.graph.viewport.setScalableY(true)
        binding.graph.title = " Your success: "
        binding.graph.isCursorMode = true
    }

}