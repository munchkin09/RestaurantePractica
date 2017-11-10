package es.carlosdevops.clc.restaurantepractica.fragment

import android.app.Activity
import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import es.carlosdevops.clc.restaurantepractica.R
import es.carlosdevops.clc.restaurantepractica.model.Table
import es.carlosdevops.clc.restaurantepractica.model.Tables

class TablesListFragment : Fragment() {


    companion object {
        fun newInstance() = TablesListFragment()
    }


    lateinit var root : View
    private var onTableSelectedListener: OnTableSelectedListener? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {



        if(inflater != null) {

            root = inflater.inflate(R.layout.fragment_list_tables,container,false)
            val tables_list = root.findViewById<ListView>(R.id.tables_list)
            if(tables_list != null) {
                tables_list.adapter = ArrayAdapter<Table>(activity, android.R.layout.simple_list_item_1, Tables.toArray())
                tables_list.setOnItemClickListener { _, _, i, _ ->
                    onTableSelectedListener?.onTableSelected(Tables.get(i),i)
                }
            }
        }
        return root
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

    private fun commonAttach(listener: Any?) {
        if(listener is OnTableSelectedListener) {
            onTableSelectedListener = listener
        }
    }

    override fun onDetach() {
        super.onDetach()
        onTableSelectedListener = null
    }

    interface OnTableSelectedListener {
        fun onTableSelected(table: Table, position: Int) {

        }
    }

}