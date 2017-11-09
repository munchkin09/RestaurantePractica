package es.carlosdevops.clc.restaurantepractica.model

data class Table(val table: String,
                 var dishes: Array<Dish>) {


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Table

        if (table != other.table) return false

        return true
    }

    override fun hashCode(): Int {
        return table.hashCode()
    }

}