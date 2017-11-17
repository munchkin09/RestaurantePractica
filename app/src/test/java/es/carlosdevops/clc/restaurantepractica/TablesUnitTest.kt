package es.carlosdevops.clc.restaurantepractica


import es.carlosdevops.clc.restaurantepractica.model.Tables
import org.junit.AfterClass
import org.junit.Assert.assertEquals
import org.junit.BeforeClass
import org.junit.Test

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
}