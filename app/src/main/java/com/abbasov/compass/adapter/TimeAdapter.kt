package com.abbasov.compass.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abbasov.compass.databinding.ItemTeachersBinding
import com.abbasov.compass.databinding.ItemTimeBinding
import com.abbasov.compass.models.TimesM

class TimeAdapter (val context: Context, val click: time, val list:List<TimesM>): RecyclerView.Adapter<TimeAdapter.Vh>(){
    inner class Vh(var item: ItemTimeBinding): RecyclerView.ViewHolder(item.root){
        fun onBind(timesM: TimesM, position: Int){
            item.times.text=timesM.times
            item.root.setOnClickListener {
                click.teach(timesM,position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeAdapter.Vh {
        return Vh(ItemTimeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TimeAdapter.Vh, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int = list.size
    interface time{
        fun teach(timesM: TimesM, position: Int)

    }
}