package es.carlosdevops.clc.restaurantepractica.model

import java.io.Serializable
import java.net.URL

/**
 * Created by carlosledesma on 6/11/17.
 * Model to contain the menu dishes
 */

data class Dish(var name: String,
                var prize: Float,
                var allergens: List<ALLERGENS>?,
                var text: String,
                var image: URL  ): Serializable {

    companion object {





        fun getAllergenFromString(str:String): ALLERGENS {

           return when(str) {
                "Paprika" -> ALLERGENS.PAPRIKA
                "Meat" -> ALLERGENS.MEAT
                "Fish" -> ALLERGENS.FISH
                "Onion" -> ALLERGENS.ONION
                "Sesame" -> ALLERGENS.SESAME
                "Milk" -> ALLERGENS.MILK
               else -> ALLERGENS.NONE
            }
        }
    }

    enum class ALLERGENS {
        PAPRIKA,
        MEAT,
        FISH,
        ONION,
        SESAME,
        MILK,
        NONE
    }



}