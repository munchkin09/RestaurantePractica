package es.carlosdevops.clc.restaurantepractica.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import es.carlosdevops.clc.restaurantepractica.BASE_URL
import es.carlosdevops.clc.restaurantepractica.R
import es.carlosdevops.clc.restaurantepractica.model.Dish
import es.carlosdevops.clc.restaurantepractica.model.Dishes
import kotlinx.android.synthetic.main.activity_initial.*
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.json.JSONObject
import java.net.URL
import java.util.*

class InitialActivity : AppCompatActivity(), View.OnClickListener {

    /*
     * En esta actividad tengo pensado hacer la descarga de datos
     * del menú e instanciar las mesas del restaurante
     */
    var menuDownloaded : List<Dish>? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_initial)
        btn_tables.setOnClickListener(this)

        downloadMenu()
    }

    private fun downloadMenu() {

        async(UI) {
            val newDataset : Deferred<List<Dish>?> = bg {
                getJSONData()
            }

            menuDownloaded = newDataset.await()
            if (menuDownloaded != null) {
                btn_tables.isEnabled = true
                Dishes.setMenu(menuDownloaded!!)
            }

        }

    }

    private fun getJSONData(): List<Dish>? {
        var menu = mutableListOf<Dish>()

        val url = URL(BASE_URL)
        try {
            val jsonString = JSONObject(Scanner(url.openStream(), "UTF-8").useDelimiter("\\A").next())

            val menuArray = jsonString.getJSONArray("menu")
            for (dishID in 0 until menuArray.length()) {

                val dish = menuArray.getJSONObject(dishID)
                val name = dish.getString("name")
                val image = URL(dish.getString("image"))
                val prize = dish.getDouble("prize").toFloat()
                val description = dish.getString("description")
                val allergensList = mutableListOf<Dish.ALLERGENS>()
                val allergens = dish.getJSONArray("allergens")

                (0 until allergens.length()).mapTo(allergensList) {
                    Dish.getAllergenFromString(allergens.getString(it))
                }

                menu.add(Dish(name,prize,allergensList,description,image))
            }
            return menu
        }catch (ex: Exception) {
            ex.printStackTrace()
        }

        return null

    }

    override fun onClick(p0: View?) {
        if (p0 == btn_tables) {
            startActivity(TablesActivity.intent(this,null,null))
        }
    }

}
