package com.abbasov.compass.models

class TeacherM {
    var name=""
    var count=""

    constructor(name: String, count: String) {
        this.name = name
        this.count = count
    }

    override fun toString(): String {
        return "TeacherM(name='$name', count='$count')"
    }

}