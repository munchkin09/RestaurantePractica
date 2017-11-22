package es.carlosdevops.clc.restaurantepractica.activity

import android.app.Activity
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

        val FOR_RESULT_DISHES = 1

        fun intent(context: Context): Intent {
            val intent = Intent(context, TablesActivity::class.java)

            isFRAGMENT_SELECTED = FRAGMENT_SELECTED.LIST

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
        startActivityForResult(intent, FOR_RESULT_DISHES)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //super.onActivityResult(requestCode, resultCode, data)

        // Este if no sería necesario ya que no podemos volver de más de una activity a esta activity
        if (requestCode == FOR_RESULT_DISHES) {

            if (resultCode == Activity.RESULT_OK) {
                isFRAGMENT_SELECTED = FRAGMENT_SELECTED.DETAIL
                //Aquí tenemos que recoger la mesa y el plato seleccionados del activityDishes
                val dish = data?.getSerializableExtra(DishesActivity.ARG_DISH) as Dish
                tableId = data?.getIntExtra(DishesActivity.ARG_TABLE,0)
                Tables[tableId!!].dishes?.add(dish)
                fragmentTableDetail = TableDetailFragment.newInstance(tableId!!)
                fragmentManager.beginTransaction()
                        .replace(R.id.tables_list_fragment,fragmentTableDetail)
                        .commit()
                supportActionBar?.title = Tables.getTableName(tableId!!)


            } else if (resultCode == Activity.RESULT_CANCELED) {
                isFRAGMENT_SELECTED = FRAGMENT_SELECTED.LIST
                tableId = data?.getIntExtra(DishesActivity.ARG_TABLE,0)
            }

        }
    }

}
