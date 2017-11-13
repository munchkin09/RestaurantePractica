package es.carlosdevops.clc.restaurantepractica.model

object Dishes {

    private var dishes = mutableListOf<Dish>()


    fun setMenu( menu: List<Dish>) {
        dishes.addAll(menu)
    }

}