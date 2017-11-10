package es.carlosdevops.clc.restaurantepractica.model

import java.io.Serializable

data class Table(val table: String,
                 var dishes: List<Dish>?) : Serializable {


    constructor(table: String) : this(table,null)

    override fun toString(): String {
        return table
    }
}