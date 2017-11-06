package es.carlosdevops.clc.restaurantepractica.model

/**
 * Created by carlosledesma on 6/11/17.
 * Model to contain the menu dishes
 */

data class Dish(var name: String, var prize: String, var allergens: Array<String>?, var text: String, var image: String  ) {

    enum class ALLERGENS {
        PAPRIKA,
        MEAT,
        FISH,
        ONION,
        SESAME,
        MILK
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Dish

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}