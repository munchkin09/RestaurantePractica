package es.carlosdevops.clc.restaurantepractica.model

import es.carlosdevops.clc.restaurantepractica.R
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
                var image: URL,
                var side_note: String? ): Serializable {

    companion object {

        fun getAllergenFromString(str: String) = when (str) {
            "Paprika" -> ALLERGENS.PAPRIKA
            "Meat" -> ALLERGENS.MEAT
            "Fish" -> ALLERGENS.FISH
            "Onion" -> ALLERGENS.ONION
            "Sesame" -> ALLERGENS.SESAME
            "Milk" -> ALLERGENS.MILK
            else -> ALLERGENS.NONE

        }

        fun getResourceFromAllergen(allergen: ALLERGENS) = when (allergen) {
                ALLERGENS.PAPRIKA -> R.drawable.ic_allergen_paprika
                ALLERGENS.MEAT -> R.drawable.ic_allergen_meat
                ALLERGENS.FISH -> R.drawable.ic_allergen_fish
                ALLERGENS.ONION -> R.drawable.ic_allergen_onion
                ALLERGENS.SESAME -> R.drawable.ic_allergen_sesame
                ALLERGENS.MILK -> R.drawable.ic_allergen_milk
                ALLERGENS.NONE -> android.R.drawable.ic_menu_info_details
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

    override fun toString() = this.name

}