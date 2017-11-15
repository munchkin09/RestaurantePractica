package es.carlosdevops.clc.restaurantepractica.model

import android.util.Log
import java.io.Serializable

object Tables {

    private var tables = mutableListOf<Table>()

    val count
        get() = tables.size

    operator fun get(i: Int) = tables[i]

    fun initTables(intTables: Int = 10) {
        Log.v("MESAS", count.toString())
        if (this.count  == 0) {
            for (table in 0 until intTables) {
                this.tables.add(table, Table("Mesa ${table+1}", mutableListOf()))
            }
        }

    }

    fun toArray() = tables.toTypedArray()

    fun getTableName(position: Int): String {
        return tables[position].table
    }

    fun getTable(position: Int) : Table {
        return tables[position]
    }
}