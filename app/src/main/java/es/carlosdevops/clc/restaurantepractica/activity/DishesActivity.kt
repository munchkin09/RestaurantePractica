package es.carlosdevops.clc.restaurantepractica.activity

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import es.carlosdevops.clc.restaurantepractica.R
import es.carlosdevops.clc.restaurantepractica.fragment.DishDetailFragment
import es.carlosdevops.clc.restaurantepractica.fragment.DishesListFragment
import es.carlosdevops.clc.restaurantepractica.model.Dish
import es.carlosdevops.clc.restaurantepractica.model.Tables

class DishesActivity : AppCompatActivity(), DishesListFragment.OnDishMenuSelectedListener, DishDetailFragment.OnDishSelectedToAddListener {

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
    var fragmentDishesList : Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dishes)

        tableId = intent.getIntExtra(ARG_TABLE,0)
        if(fragmentManager.findFragmentById(R.id.dishes_list_fragment) == null) {
            fragmentDishesList = DishesListFragment.newInstance()
            fragmentManager.beginTransaction()
                    .add(R.id.dishes_list_fragment,fragmentDishesList)
                    .commit()
        }
    }

    override fun onDishMenuSelectedInteraction(position: Int) {

        val fragment = DishDetailFragment.newInstance(position)

        fragmentManager.beginTransaction()
                .remove(fragmentDishesList)
                .add(R.id.dishes_list_fragment,fragment)
                .commit()


    }

    override fun onDishSelectedToAdd(dish: Dish) {

        val intent = Intent()
        intent.putExtra(ARG_TABLE,tableId!!)
        intent.putExtra(ARG_DISH,dish)
        setResult(Activity.RESULT_OK,intent)
        finish()
    }

    override fun onCancelSelected() {

        val intent = Intent()
        intent.putExtra(ARG_TABLE,tableId!!)
        setResult(Activity.RESULT_CANCELED,intent)
        finish()
    }
}
