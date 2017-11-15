package es.carlosdevops.clc.restaurantepractica.fragment

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

import es.carlosdevops.clc.restaurantepractica.extensions.loadImage
import es.carlosdevops.clc.restaurantepractica.R
import es.carlosdevops.clc.restaurantepractica.model.Dish
import es.carlosdevops.clc.restaurantepractica.model.Dishes
import kotlinx.android.synthetic.main.fragment_dish_detail.*


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DishDetailFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DishDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DishDetailFragment : Fragment() {

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val DISH_POSITION = "DISH_POSITION"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param dish_position Parameter 1.
         * @return A new instance of fragment DishDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(dish_position: Int): DishDetailFragment {
            val fragment = DishDetailFragment()
            val args = Bundle()
            args.putInt(DISH_POSITION, dish_position)
            fragment.arguments = args
            return fragment
        }
    }

    // TODO: Rename and change types of parameters

    var root : View? = null
    private var dish_position: Int? = null
    lateinit var dish : Dish

    private var mListener: OnDishSelectedToAddListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            dish_position = arguments.getInt(DISH_POSITION)

        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        root = inflater!!.inflate(R.layout.fragment_dish_detail, container, false)

        dish = Dishes.getDish(dish_position!!)

        return root
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view?.findViewById<ImageView>(R.id.imgv_dish)?.loadImage(dish.image)
        view?.findViewById<TextView>(R.id.txt_dish_description)?.text = dish.text
        view?.findViewById<Button>(R.id.btn_add_dish)?.setOnClickListener {
            onButtonPressed()
        }

        view?.findViewById<Button>(R.id.btn_cancel)?.setOnClickListener {  }


    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed() {
        if (mListener != null) {

            dish.side_note = view?.findViewById<EditText>(R.id.txt_side_note)?.text.toString()
            mListener!!.onDishSelectedToAdd(dish)
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
        if (context is OnDishSelectedToAddListener) {
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
    interface OnDishSelectedToAddListener {
        // TODO: Update argument type and name
        fun onDishSelectedToAdd(dish: Dish)
    }


}// Required empty public constructor
