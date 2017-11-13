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
import es.carlosdevops.clc.restaurantepractica.model.Table
import es.carlosdevops.clc.restaurantepractica.model.Tables
import kotlinx.android.synthetic.main.activity_tables.*

class TablesActivity : AppCompatActivity(),TablesListFragment.OnTableSelectedListener,TableDetailFragment.OnAddDishClickListener {

    companion object {
        val EXTRA_DISHES = "EXTRA_DISHES"

        fun intent (context: Context): Intent {
            val intent = Intent(context,TablesActivity::class.java)
            //intent.putExtra(EXTRA_DISHES,menu)
            return intent

        }
    }


    var fragmentTableList : Fragment? = null
    var fragmentTableDetail: Fragment? = null

    var fragmentID: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tables)

        if(tables_list_fragment != null) {

             fragmentTableList = TablesListFragment.newInstance()
            fragmentManager.beginTransaction()
                    .add(R.id.tables_list_fragment,fragmentTableList)
                    .commit()
            supportActionBar?.title = getString(R.string.sab_tables)

        }
    }

    override fun onTableSelected(table: Table, position: Int) {

       fragmentTableDetail = TableDetailFragment.newInstance(position)
        fragmentManager.beginTransaction()
                .replace(R.id.tables_list_fragment,fragmentTableDetail)
                .commit()
        supportActionBar?.title = Tables.getTableName(position)

    }

    override fun onAddDishClick() {

    }

    override fun onBackPressed() {

        //super.onBackPressed()
//        Log.v("BACK_BUTTON","Paso hacia atrás")
//        if(fragmentManager.equals(fragmentTableList) == false) {
//
            fragmentManager.beginTransaction()
                    .remove(fragmentTableDetail)
                    .replace(R.id.tables_list_fragment, fragmentTableList)
                    .commit()
            supportActionBar?.title = getString(R.string.sab_tables)
//
//        } else {
//            super.onBackPressed()
//        }
    }
}
