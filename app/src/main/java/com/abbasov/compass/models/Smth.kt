package com.abbasov.compass.models

class Smth {
    var teach=""
    var day=""

    constructor(teach: String, day: String) {
        this.teach = teach
        this.day = day
    }

    override fun toString(): String {
        return "Smth(teach='$teach', day='$day')"
    }


}