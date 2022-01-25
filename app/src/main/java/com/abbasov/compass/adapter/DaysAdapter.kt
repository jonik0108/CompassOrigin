package com.abbasov.compass.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abbasov.compass.databinding.ItemDaysBinding
import com.abbasov.compass.databinding.ItemTeachersBinding
import com.abbasov.compass.models.DaysM
import com.abbasov.compass.models.TeacherM

class DaysAdapter (val context: Context, val click: days, val list:List<DaysM>): RecyclerView.Adapter<DaysAdapter.Vh>(){
    inner class Vh(var item: ItemDaysBinding): RecyclerView.ViewHolder(item.root){
        fun onBind(daysM: DaysM, position: Int){
            item.days.text=daysM.days
            item.root.setOnClickListener {
                click.teach(daysM,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaysAdapter.Vh {
        return Vh(ItemDaysBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: DaysAdapter.Vh, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int = list.size
    interface days{
        fun teach(person: DaysM, position: Int)
    }
}