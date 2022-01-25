package com.abbasov.compass

import android.annotation.SuppressLint
import android.os.Bundle
import android.telephony.SmsManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.abbasov.compass.adapter.PersonAdapter
import com.abbasov.compass.databinding.FragmentBirthBinding
import com.abbasov.compass.room.AppDatabase
import com.abbasov.compass.room.Person
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class Birth : Fragment() {

    lateinit var binding: FragmentBirthBinding
    lateinit var appDatabase: AppDatabase
    lateinit var personAdapter: PersonAdapter

    @SuppressLint("SimpleDateFormat")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBirthBinding.inflate(LayoutInflater.from(context))
        appDatabase = AppDatabase.getInstance(requireContext())
        val arrayPerson = ArrayList<Person>()
        arrayPerson.addAll(appDatabase.personDao().getAllPerson())
        val format = SimpleDateFormat("dd.MM").format(Date())
        val array1 = arrayPerson.filter { person ->
            person.birth!!.contains(format)
        }
        binding.send.setOnClickListener {
            for (i in array1) {
                val textb = "Поздравляем ${i.surname} ${i.name} с днем рождения! Желаем достижения лучших результатов как в учёбе так и в жизни. С уважением учебный центр COMPASS\n" +
                        ""
                if (textb=="") {
                    Toast.makeText(context, "введите текст", Toast.LENGTH_SHORT).show()
                } else {
                    val sms = SmsManager.getDefault()
                    val parts = sms.divideMessage(textb)
                    sms.sendMultipartTextMessage(i.number, null, parts, null, null)

                }

            }

        }

    personAdapter = PersonAdapter(requireContext(),
    object : PersonAdapter.person {
        override fun teach(person: Person, position: Int) {

        }
    }, array1)
    binding.rv.adapter = personAdapter
    return binding.root
}


}