package com.abbasov.compass.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.abbasov.compass.R
import com.abbasov.compass.adapter.NeoAdapter
import com.abbasov.compass.databinding.FragmentStartBinding
import com.abbasov.compass.models.Firebase
import com.abbasov.compass.models.Menu
import com.abbasov.compass.room.AppDatabase
import com.abbasov.compass.room.Person
import com.google.firebase.database.*


class Start : Fragment() {
    lateinit var appDatabase: AppDatabase
    lateinit var binding: FragmentStartBinding
    lateinit var neoAdapter: NeoAdapter
    lateinit var firebaseDatabase:FirebaseDatabase
    lateinit var reference: DatabaseReference
    private  val TAG = "Start"




    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentStartBinding.inflate(LayoutInflater.from(context))
        appDatabase = AppDatabase.getInstance(requireContext())
        val arrray=ArrayList<Person>()
       /* firebaseDatabase= FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference("users")
        reference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val children = snapshot.children
                for (child in children) {
                    val value = child.getValue(Person::class.java)
                    if(value!=null){
                        Log.d(TAG, "onDataChange: $value ")

                        appDatabase.personDao().addPerson(value)


                    }
                }


            }
            override fun onCancelled(error: DatabaseError) {

            }
        })*/


        val array=ArrayList<Menu>()
        array.add(Menu("TEACHERS",R.drawable.list))
        array.add(Menu("ADD",R.drawable.ic_baseline_add_circle_outline_24))
        array.add(Menu("MEMBERS",R.drawable.list))
        array.add(Menu("MASS",R.drawable.ic_baseline_send_24))
        array.add(Menu("BIRTH",R.drawable.ic_baseline_date_range_24))
        neoAdapter= NeoAdapter(requireContext(),object : NeoAdapter.Click{
            override fun click(menu: Menu, position: Int) {
                if (position==0){
                    findNavController().navigate(R.id.teachers)
                }else if (position==1){
                    findNavController().navigate(R.id.addPeople)
                }else if (position==2){
                    findNavController().navigate(R.id.allPerson)
                }else if (position==3){
                    findNavController().navigate(R.id.smsmass)
                }else if (position==4){
                    findNavController().navigate(R.id.birth2)

                }

            }
        },array)
        binding.rv.adapter=neoAdapter
        binding.home.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.home.setShapeType(1)
                    android.os.Handler().postDelayed({
                        binding.home.setShapeType(0)
                    }, 100)
                }
            }
            v?.onTouchEvent(event) ?: true
        }
        binding.add.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.add.setShapeType(1)
                    android.os.Handler().postDelayed({
                        binding.add.setShapeType(0)
                    }, 100)
                }
            }
            v?.onTouchEvent(event) ?: true
        }

        binding.notif.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.notif.setShapeType(1)
                    android.os.Handler().postDelayed({
                        binding.notif.setShapeType(0)
                    }, 100)
                }
            }
            v?.onTouchEvent(event) ?: true
        }
        binding.settings.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.settings.setShapeType(1)
                    android.os.Handler().postDelayed({
                        binding.settings.setShapeType(0)
                    }, 100)
                }
            }
            v?.onTouchEvent(event) ?: true
        }

/*
        binding.count.text = "${arrayPerson.size}"
*/


        return binding.root
    }

}