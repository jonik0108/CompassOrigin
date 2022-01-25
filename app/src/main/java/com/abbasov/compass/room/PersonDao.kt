package com.abbasov.compass.room

import androidx.room.*

@Dao
interface PersonDao {

    @Query("select * from person")
    fun getAllPerson():List<Person>

    @Insert
    fun addPerson(person: Person)

    @Delete
    fun deletePerson(person: Person)

    @Update
    fun editPerson(person: Person)

}