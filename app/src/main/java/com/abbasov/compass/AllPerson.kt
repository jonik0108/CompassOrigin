package com.abbasov.compass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.abbasov.compass.adapter.PersonAdapter
import com.abbasov.compass.databinding.FragmentAllPersonBinding
import com.abbasov.compass.room.AppDatabase
import com.abbasov.compass.room.Person


class AllPerson : Fragment() {

    lateinit var binding:FragmentAllPersonBinding
    lateinit var appDatabase: AppDatabase
    lateinit var personAdapter: PersonAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllPersonBinding.inflate(LayoutInflater.from(context))
        appDatabase = AppDatabase.getInstance(requireContext())
        val arrayPerson=ArrayList<Person>()
        arrayPerson.addAll(appDatabase.personDao().getAllPerson())
        binding.count.text=arrayPerson.size.toString()
        personAdapter= PersonAdapter(requireContext(),object :PersonAdapter.person{
            override fun teach(person: Person, position: Int) {
                Toast.makeText(context, "$position", Toast.LENGTH_SHORT).show()
            }
        },arrayPerson)
        binding.rv.adapter=personAdapter


        return binding.root
    }

    override fun onResume() {
        super.onResume()

    }

}