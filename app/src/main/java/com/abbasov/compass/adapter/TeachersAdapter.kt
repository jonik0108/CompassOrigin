package com.abbasov.compass.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abbasov.compass.databinding.ItemTeachersBinding
import com.abbasov.compass.models.TeacherM

class TeachersAdapter (val context: Context, val click: teach, val list:List<TeacherM>): RecyclerView.Adapter<TeachersAdapter.Vh>(){
    inner class Vh(var item: ItemTeachersBinding): RecyclerView.ViewHolder(item.root){
        fun onBind(teachers: TeacherM, position: Int){
            item.txtTeachers.text=teachers.name
            item.count.text=teachers.count
            item.root.setOnClickListener {
                click.teach(teachers,position)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeachersAdapter.Vh {
        return Vh(ItemTeachersBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: TeachersAdapter.Vh, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int = list.size
    interface teach{
        fun teach(person: TeacherM, position: Int)

    }
}