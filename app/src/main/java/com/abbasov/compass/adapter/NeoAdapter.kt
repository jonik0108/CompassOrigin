package com.abbasov.compass.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.abbasov.compass.R
import com.abbasov.compass.databinding.ItemDaysBinding
import com.abbasov.compass.databinding.ItemNeoBinding
import com.abbasov.compass.models.DaysM
import com.abbasov.compass.models.Menu

class NeoAdapter (val context: Context,val click:Click, val list:List<Menu>): RecyclerView.Adapter<NeoAdapter.Vh>(){
    inner class Vh(var item: ItemNeoBinding): RecyclerView.ViewHolder(item.root){
        @SuppressLint("ClickableViewAccessibility")
        fun onBind(menu: Menu, position: Int){
            item.name.text=menu.name
            item.imageView.setImageResource(menu.image)
            item.root.setOnClickListener {
                click.click(menu, position)

            }
            item.card.setOnTouchListener { v, event ->
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> {
                        item.card.setShapeType(1)
                        android.os.Handler().postDelayed({
                            item.card.setShapeType(0)
                        }, 100)

                    }


                }

                v?.onTouchEvent(event) ?: true
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NeoAdapter.Vh {
        return Vh(ItemNeoBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: NeoAdapter.Vh, position: Int) {
        holder.onBind(list[position],position)
    }

    override fun getItemCount(): Int = list.size
    interface Click{
        fun click(menu: Menu,position: Int)
    }

}