
package com.example.android.rvtutorial
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.android_app.CellClickListener
import kotlinx.android.synthetic.main.animal_list_item.view.*
import com.example.android_app.R
import org.json.JSONArray
import org.json.JSONObject
import androidx.navigation.findNavController

class CustomAdapter(val items: ArrayList<Any>,val cellClickListener : CellClickListener) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size
    }
    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.animal_list_item, parent, false))
    }

    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val jsonArray = JSONArray(items)
        val jsonObject: JSONObject = jsonArray.getJSONObject(position)
        val nameSong= jsonObject.get("name_song")

        holder?.tvAnimalType?.text = nameSong as CharSequence?
        holder?.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(nameSong)
        }
    }
}

class   ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvAnimalType = view.tv_animal_type
}