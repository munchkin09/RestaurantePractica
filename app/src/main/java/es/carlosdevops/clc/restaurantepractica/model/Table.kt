package es.carlosdevops.clc.restaurantepractica.model

import java.io.Serializable

data class Table(val table: String,
                 var dishes: MutableList<Dish>?) : Serializable {


    constructor(table: String) : this(table,null)

    fun calculateBill() : Float {

        var totalBill = 0f
        if (dishes != null) {

            dishes?.forEach { dish ->
                totalBill += dish.prize
            }
        }

        return totalBill
    }

    override fun toString(): String {
        return table
    }

}