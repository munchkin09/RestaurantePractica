package es.carlosdevops.clc.restaurantepractica


import es.carlosdevops.clc.restaurantepractica.model.Dish
import es.carlosdevops.clc.restaurantepractica.model.Table
import es.carlosdevops.clc.restaurantepractica.model.Tables
import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test
import java.net.URL

class TablesUnitTest {

    companion object {

        @BeforeClass
        @JvmStatic fun setup() {
            Tables.initTables(5)
        }

        @AfterClass
        @JvmStatic fun teardown() {
            // clean up after this class, leave nothing dirty behind
        }
    }

    @Test
    fun tablesCountIsNotNull() {
        assertEquals(5, Tables.count)
    }

    @Test
    fun tableNameIsNotNull() {
        assertEquals("Mesa 1",Tables.getTableName(0))
    }

    @Test
    fun tableAddADish() {
        Tables[0].dishes?.add(Dish("Plato 1",13f, listOf(Dish.ALLERGENS.MILK, Dish.ALLERGENS.SESAME),"Blablabla", URL("http://google.com"),null))
        assertEquals(1,Tables[0].dishes?.count())
    }

    @Test
    fun tableBillIsNotNull() {
        assertEquals(13f,Tables[0].calculateBill())
    }
}