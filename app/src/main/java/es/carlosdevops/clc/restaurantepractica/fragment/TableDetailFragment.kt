package es.carlosdevops.clc.restaurantepractica.fragment

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.app.Fragment
import android.view.*
import android.widget.ArrayAdapter
import android.widget.ListView
import es.carlosdevops.clc.restaurantepractica.R
import es.carlosdevops.clc.restaurantepractica.model.Dish
import es.carlosdevops.clc.restaurantepractica.model.Tables


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [TableDetailFragment.OnAddDishClickListener] interface
 * to handle interaction events.
 * Use the [TableDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TableDetailFragment : Fragment() {

    companion object {

        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val TABLE_POSITION = "TABLE_POSITION"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param position The table clicked on TablesListFragment.
         * @return A new instance of fragment TableDetailFragment.
         */
        fun newInstance(position: Int): TableDetailFragment {
            val fragment = TableDetailFragment()
            val args = Bundle()
            args.putInt(TABLE_POSITION, position)
            fragment.arguments = args
            return fragment
        }
    }

    private var tableId: Int? = null


    private var mListener: OnAddDishClickListener? = null
    var root: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            tableId = arguments.getInt(TABLE_POSITION)

        }
        setHasOptionsMenu(true)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        if (inflater != null) {
            // Inflate the layout for this fragment
            root = inflater.inflate(R.layout.fragment_table_detail, container, false)
            val dish_list_per_table = root?.findViewById<ListView>(R.id.list_dishes_per_table)
            if (dish_list_per_table != null) {
                dish_list_per_table.adapter = ArrayAdapter<Dish>(activity,android.R.layout.simple_list_item_1, Tables.get(tableId!!).dishes?.toTypedArray())

            }
        }
        return root
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed() {
        if (mListener != null) {
            mListener!!.onAddDishClick()
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

    fun commonAttach(context: Any?) {
        if (context is OnAddDishClickListener) {
            mListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.table_options,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item != null) {
            if (item.itemId == R.id.btn_add_new_dish) {
                this.onButtonPressed()

            } else if (item.itemId == R.id.btn_calculate_bill) {
                val bill = Tables.getTable(tableId!!).calculateBill()
                val simpleAlert = AlertDialog.Builder(activity).create()

                simpleAlert.setTitle("La cuenta")
                simpleAlert.setMessage("Show simple Alert")

                simpleAlert.setButton(AlertDialog.BUTTON_POSITIVE, getString(android.R.string.ok), {
                    _, _ ->
                    simpleAlert.dismiss()
                })

                simpleAlert.show()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
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
    interface OnAddDishClickListener {

        fun onAddDishClick()
    }
}
