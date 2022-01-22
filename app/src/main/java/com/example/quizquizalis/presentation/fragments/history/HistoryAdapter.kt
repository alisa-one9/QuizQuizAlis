package com.example.quizquizalis.presentation.fragments.history

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.quizquizalis.R
import com.example.quizquizalis.data.local.RoomResultModel
import com.example.quizquizalis.databinding.ItemHistoryBinding
import com.example.quizquizalis.ext.deleteDialog
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@SuppressLint("NotifyDataSetChanged")

class HistoryAdapter(
    private val list: ArrayList<RoomResultModel>,
    private var onPopupMenuClickListener: (history:RoomResultModel) ->Unit
   ) : RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_history,parent,false)
            return HistoryHolder(view)

    }

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
         holder.onBind(list[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class HistoryHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var binding: ItemHistoryBinding = ItemHistoryBinding.bind(itemView)


        init {
            binding.popUpMenu.setOnClickListener {
                deleteDialog(itemView.context,"Delete dialog","Are you sure delete?",this::delete)
            }
        }

        private fun delete() {
            onPopupMenuClickListener(list[layoutPosition])
            list.removeAt(layoutPosition)
            notifyDataSetChanged()
        }

        fun onBind(roomResultModel: RoomResultModel) {

            binding.apply {
                textViewCategory.text = roomResultModel.category
                "${roomResultModel.correctAnswers}/${roomResultModel.amount}".also { textViewCorrectAns.text = it }
                textViewDifficulty.text = roomResultModel.difficulty
                textViewData.text = convertLongToTime(roomResultModel.date)
            }

        }

        @SuppressLint("SimpleDateFormat")
        fun convertLongToTime(time: Long): String {
            val date = Date(time)
            val format = SimpleDateFormat(itemView.context.getString(R.string.date_format))
            return format.format(date)
        }

    }


}