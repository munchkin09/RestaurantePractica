package es.carlosdevops.clc.restaurantepractica.activity

import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import es.carlosdevops.clc.restaurantepractica.R
import es.carlosdevops.clc.restaurantepractica.fragment.TableDetailFragment
import es.carlosdevops.clc.restaurantepractica.fragment.TablesListFragment
import es.carlosdevops.clc.restaurantepractica.model.Dish
import es.carlosdevops.clc.restaurantepractica.model.Dishes
import es.carlosdevops.clc.restaurantepractica.model.Table
import es.carlosdevops.clc.restaurantepractica.model.Tables
import kotlinx.android.synthetic.main.activity_tables.*

class TablesActivity : AppCompatActivity(),TablesListFragment.OnTableSelectedListener,TableDetailFragment.OnAddDishClickListener {

    companion object {
        val ARG_DISH = "DISH"
        val ARG_TABLE = "TABLE"

        fun intent(context: Context, dish: Dish?, table: Int?): Intent {
            val intent = Intent(context, TablesActivity::class.java)
            if (dish != null) {
                intent.putExtra(ARG_DISH, dish)
            }
            if (table != null) {
                intent.putExtra(ARG_TABLE, table)
                isFRAGMENT_SELECTED = FRAGMENT_SELECTED.DETAIL
            } else {
                isFRAGMENT_SELECTED = FRAGMENT_SELECTED.LIST
            }
            return intent

        }

        var isFRAGMENT_SELECTED = Companion.FRAGMENT_SELECTED.LIST

        enum class FRAGMENT_SELECTED {
            LIST,
            DETAIL
        }
    }


    var fragmentTableList: Fragment? = null
    var fragmentTableDetail: Fragment? = null

    var tableId: Int? = null
    var dish: Dish? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tables)
        if (TablesActivity.isFRAGMENT_SELECTED == FRAGMENT_SELECTED.LIST) {
            if (tables_list_fragment != null) {

                fragmentTableList = TablesListFragment.newInstance()
                fragmentManager.beginTransaction()
                        .add(R.id.tables_list_fragment, fragmentTableList)
                        .commit()
                supportActionBar?.title = getString(R.string.sab_tables)

            }
        } else {
            tableId = intent?.getIntExtra(ARG_TABLE, 0)
            dish = intent?.getSerializableExtra(ARG_DISH) as? Dish

            fragmentTableDetail = TableDetailFragment.newInstance(tableId!!)
            fragmentManager.beginTransaction()
                    .replace(R.id.tables_list_fragment,fragmentTableDetail)
                    .commit()
            supportActionBar?.title = Tables.getTableName(tableId!!)
        }
    }

    override fun onTableSelected(table: Table, position: Int) {

       fragmentTableDetail = TableDetailFragment.newInstance(position)
        fragmentManager.beginTransaction()
                .replace(R.id.tables_list_fragment,fragmentTableDetail)
                .commit()
        supportActionBar?.title = Tables.getTableName(position)
        tableId = position

    }

    override fun onAddDishClick() {

        val intent = DishesActivity.intent(this,tableId!!)
        fragmentManager.beginTransaction()
                .remove(fragmentTableDetail)
                .commit()
        startActivity(intent)
    }

 /*   override fun onBackPressed() {

        super.onBackPressed()
//        Log.v("BACK_BUTTON","Paso hacia atrás")
//        if(fragmentManager.equals(fragmentTableList) == false) {
//
//        fragmentManager.beginTransaction()
//                .replace(R.id.tables_list_fragment, fragmentTableList)
//                .addToBackStack("")
//                .commit()
//        supportActionBar?.title = getString(R.string.sab_tables)
//
//        } else {
//            super.onBackPressed()
//        }
    }*/
}
