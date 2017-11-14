package es.carlosdevops.clc.restaurantepractica.activity

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import es.carlosdevops.clc.restaurantepractica.R
import es.carlosdevops.clc.restaurantepractica.fragment.DishesListFragment

class DishesActivity : AppCompatActivity(), DishesListFragment.OnFragmentInteractionListener {

    companion object {
        val ARG_DISH = "DISH"
        val ARG_TABLE = "TABLE"

        fun intent (context: Context, table: Int): Intent {
            val intent = Intent(context,DishesActivity::class.java)
            intent.putExtra(ARG_TABLE,table)
            return intent

        }
    }

    var tableId : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dishes)

        tableId = intent.getIntExtra(ARG_TABLE,0)
        if(fragmentManager.findFragmentById(R.id.dishes_list_fragment) == null) {
            val fragment = DishesListFragment.newInstance("","")
            fragmentManager.beginTransaction()
                    .add(R.id.dishes_list_fragment,fragment)
                    .commit()
        }
    }

    override fun onFragmentInteraction() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
