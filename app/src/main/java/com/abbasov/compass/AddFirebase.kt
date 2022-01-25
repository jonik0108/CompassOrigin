package com.abbasov.compass

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.abbasov.compass.databinding.FragmentAddFirebaseBinding
import com.abbasov.compass.models.Firebase
import com.abbasov.compass.room.AppDatabase
import com.abbasov.compass.room.Person
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.ArrayList


class AddFirebase : Fragment() {

    lateinit var binding:FragmentAddFirebaseBinding
    lateinit var firebaseDatabase: FirebaseDatabase
    lateinit var reference: DatabaseReference
    lateinit var appDatabase: AppDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentAddFirebaseBinding.inflate(LayoutInflater.from(requireContext()))
        appDatabase = AppDatabase.getInstance(requireContext())
        val list =ArrayList<Person>()
        list.addAll(appDatabase.personDao().getAllPerson())
        firebaseDatabase= FirebaseDatabase.getInstance()
        reference=firebaseDatabase.getReference("users")
//        Toast.makeText(context, "${list.size}", Toast.LENGTH_SHORT).show()
        for (person in list) {
            val key = reference.push().key
            reference.child(key!!).setValue(person)

        }
        return binding.root

    }


}