package es.carlosdevops.clc.restaurantepractica

import es.carlosdevops.clc.restaurantepractica.model.Dish
import es.carlosdevops.clc.restaurantepractica.model.Dishes

import org.junit.AfterClass
import org.junit.Test
import org.junit.Assert.*
import org.junit.BeforeClass
import java.net.URL


class DishesUnitTest {

    companion object {

        @BeforeClass
        @JvmStatic fun setup() {
            Dishes.setMenu(mutableListOf(Dish("Plato 1",13f, listOf(Dish.ALLERGENS.MILK,Dish.ALLERGENS.SESAME),"Blablabla", URL("http://google.com"),null)))
        }

        @AfterClass
        @JvmStatic fun teardown() {
            // clean up after this class, leave nothing dirty behind
        }
    }


    @Test
    fun dishesCountIsNotNull() {
        assertEquals(1, Dishes.count())
    }

    @Test
    fun dishesGetDishIsEqual() {
        assertEquals(Dishes.getDish(0),Dishes.getDish(0))
    }


}