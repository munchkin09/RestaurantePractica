package es.carlosdevops.clc.restaurantepractica.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import es.carlosdevops.clc.restaurantepractica.R
import es.carlosdevops.clc.restaurantepractica.fragment.TableDetailFragment
import es.carlosdevops.clc.restaurantepractica.fragment.TablesListFragment
import es.carlosdevops.clc.restaurantepractica.model.Table
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


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tables)

        if(tables_list_fragment != null) {

            val fragment = TablesListFragment.newInstance()
            fragmentManager.beginTransaction()
                    .add(R.id.tables_list_fragment,fragment)
                    .commit()

        }


    }

    override fun onTableSelected(table: Table, position: Int) {
        if(fragmentManager.findFragmentById(R.id.tables_list_fragment) != null) {

            //fragmentManager.beginTransaction()
        }
    }

    override fun onAddDishClick() {

    }
}
