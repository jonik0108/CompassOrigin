package com.abbasov.compass

import android.os.Bundle
import android.telephony.SmsManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.abbasov.compass.adapter.PersonAdapter
import com.abbasov.compass.databinding.FragmentSmsmassBinding
import com.abbasov.compass.room.AppDatabase
import com.abbasov.compass.room.Person


class Smsmass : Fragment() {

    lateinit var binding:FragmentSmsmassBinding
    lateinit var appDatabase: AppDatabase
    lateinit var personAdapter: PersonAdapter
    var a=0
    var b=0
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSmsmassBinding.inflate(LayoutInflater.from(context))
        appDatabase = AppDatabase.getInstance(requireContext())
        val arrayPerson=ArrayList<Person>()
        arrayPerson.addAll(appDatabase.personDao().getAllPerson())
        personAdapter= PersonAdapter(requireContext(),object :PersonAdapter.person{
            override fun teach(person: Person, position: Int) {

            }
        },arrayPerson)
        binding.rv.adapter=personAdapter
        binding.sendmass.setOnClickListener {
            val v=binding.masstext.text.toString()
            if (a==0 && v!="") {
                for (i in arrayPerson) {
                    val number = i.number.toString()
                    val text = binding.masstext.text.toString()
                    val sms = SmsManager.getDefault()
                    val parts = sms.divideMessage(text)
                    sms.sendMultipartTextMessage(number, null, parts, null, null)

                }
                a++
                Toast.makeText(context, "send", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "Сообщения уже отправлены", Toast.LENGTH_SHORT).show()
            }
        }
/*
        binding.sendmass2.setOnClickListener {
            val v2=binding.masstext.text.toString()
            if (b==0 && v2!="") {
                for (i2 in array2) {
                    val number = i2.number.toString()
                    val text = binding.masstext.text.toString()
                    val sms = SmsManager.getDefault()
                    val parts = sms.divideMessage(text)
                    sms.sendMultipartTextMessage(number, null, parts, null, null)
                }
                b++
                Toast.makeText(context, "send", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context, "Сообщения уже отправлены", Toast.LENGTH_SHORT).show()
            }
        }
*/


        return binding.root
    }

}