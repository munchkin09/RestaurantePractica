package es.carlosdevops.clc.restaurantepractica.fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.app.Fragment
import android.view.*
import es.carlosdevops.clc.restaurantepractica.R


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

    private var table_position: Int? = null


    private var mListener: OnAddDishClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            table_position = arguments.getInt(TABLE_POSITION)

        }
        setHasOptionsMenu(true)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment


        return inflater!!.inflate(R.layout.fragment_table_detail, container, false)
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
