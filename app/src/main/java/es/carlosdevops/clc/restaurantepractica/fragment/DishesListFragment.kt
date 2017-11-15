package es.carlosdevops.clc.restaurantepractica.fragment

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView

import es.carlosdevops.clc.restaurantepractica.R
import es.carlosdevops.clc.restaurantepractica.model.Dish
import es.carlosdevops.clc.restaurantepractica.model.Dishes
import kotlinx.android.synthetic.main.fragment_list_tables.*

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DishesListFragment.OnDishMenuSelectedListener] interface
 * to handle interaction events.
 * Use the [DishesListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DishesListFragment : Fragment() {


    companion object {

        fun newInstance() = DishesListFragment()
    }

    private var mListener: OnDishMenuSelectedListener? = null
    lateinit var root : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (inflater != null) {
            // Inflate the layout for this fragment
            root = inflater.inflate(R.layout.fragment_dishes_list, container, false)
            val dishesList = root.findViewById<ListView>(R.id.dishes_list)
            if (dishesList != null) {
                dishesList.adapter = ArrayAdapter<Dish>(activity, android.R.layout.simple_list_item_1, Dishes.toArray())
                dishesList.setOnItemClickListener { _, _, i, _ ->
                    onButtonPressed(i)
                }
            }
        }
        return root
    }
    
    fun onButtonPressed(position: Int) {
        if (mListener != null) {
            mListener!!.onDishMenuSelectedInteraction(position)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonAttach(context)
    }


    @Suppress("OverridingDeprecatedMember","DEPRECATION")
    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonAttach(activity)
    }

    private fun commonAttach(context: Any?) {
        if (context is OnDishMenuSelectedListener) {
            mListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html) for more information.
     */
    interface OnDishMenuSelectedListener {

        fun onDishMenuSelectedInteraction(position: Int)
    }


}
