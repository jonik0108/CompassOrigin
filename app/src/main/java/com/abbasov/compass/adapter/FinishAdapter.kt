package com.abbasov.compass.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abbasov.compass.databinding.ItemFinishBinding
import com.abbasov.compass.room.Person

class FinishAdapter (val context: Context, val click: finish, val list:List<Person>): RecyclerView.Adapter<FinishAdapter.Vh>(){
    inner class Vh(var item: ItemFinishBinding): RecyclerView.ViewHolder(item.root){
        fun onBind(person: Person, position: Int){
            item.name1.text=person.name
            item.surname1.text=person.surname
            item.number1.text=person.number
            item.birth1.text=person.birth
            item.subject.text=person.subject
            item.time1.text=person.time
            item.week1.text=person.week
            item.numberteach.text=person.numberteach
            item.delete.setOnClickListener {
                click.delete(person, position)

            }
            item.root.setOnClickListener{
                click.teach(person,position)
            }
            item.sendsms.setOnClickListener {
                click.send(person,position)
            }
            item.root.setOnLongClickListener {
                click.refactor(person, position)
                true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FinishAdapter.Vh {
        return Vh(ItemFinishBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FinishAdapter.Vh, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int = list.size
    interface finish{
        fun teach(person: Person, position: Int)
        fun send(person: Person, position: Int)
        fun delete(person: Person, position: Int)
        fun refactor(person: Person, position: Int)
    }
}