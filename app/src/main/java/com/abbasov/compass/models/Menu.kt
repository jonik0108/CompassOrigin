package com.abbasov.compass.models

class Menu {
    var name=""
    var image=0

    constructor(name: String, image: Int) {
        this.name = name
        this.image = image
    }


    override fun toString(): String {
        return "Menu(name='$name', image=$image)"
    }

}