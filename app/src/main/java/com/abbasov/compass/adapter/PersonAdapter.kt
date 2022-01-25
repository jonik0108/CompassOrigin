package com.abbasov.compass.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abbasov.compass.databinding.ItemPersonBinding
import com.abbasov.compass.room.Person

class PersonAdapter (val context: Context, val click: person, val list:List<Person>): RecyclerView.Adapter<PersonAdapter.Vh>(){
    inner class Vh(var item: ItemPersonBinding): RecyclerView.ViewHolder(item.root){
        fun onBind(person: Person, position: Int){
            item.name1.text=person.name
            item.surname1.text=person.surname
            item.number1.text=person.number
            item.birth1.text=person.birth
            item.teacher1.text=person.teacher
            item.time1.text=person.time
            item.week1.text=person.week
            item.root.setOnClickListener {
                click.teach(person,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonAdapter.Vh {
        return Vh(ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PersonAdapter.Vh, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int = list.size
    interface person{
        fun teach(person: Person, position: Int)
    }
}