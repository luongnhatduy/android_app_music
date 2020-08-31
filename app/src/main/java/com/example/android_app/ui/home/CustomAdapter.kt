package com.example.android.rvtutorial

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.android_app.CellClickListener
import com.example.android_app.IMG_URL
import com.example.android_app.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.animal_list_item.view.*
import kotlinx.android.synthetic.main.item_loading.view.*
import org.json.JSONArray
import org.json.JSONObject
import java.util.logging.Handler


class CustomAdapter(var items: ArrayList<Any>, val cellClickListener: CellClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val VIEW_TYPE_ITEM = 0
    private val VIEW_TYPE_LOADING = 1
    var plusItem: Int = 0

    // Gets the number of animals in the list
    override fun getItemCount(): Int {
        return items.size + plusItem
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        Log.e("view Type", "${viewType}")

        if (viewType === VIEW_TYPE_ITEM) {
            return ViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.animal_list_item, parent, false)
            )
        } else {
            return LoadingView(
                LayoutInflater.from(parent.context).inflate(R.layout.item_loading, parent, false)
            )
        }
    }


    // Binds each animal in the ArrayList to a view
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            populateItemRows(holder, position)
        } else if (holder is LoadingView) {
            showLoadingView(holder, position)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (items.size == position ) VIEW_TYPE_LOADING else VIEW_TYPE_ITEM
    }

    private fun showLoadingView(viewHolder: LoadingView, position: Int) {
        //ProgressBar would be displayed
    }

    private fun populateItemRows(holder: ViewHolder, position: Int) {
        val jsonArray = JSONArray(items)
        val jsonObject: JSONObject = jsonArray.getJSONObject(position)
        val movieId = jsonObject.get("id")
        val title = jsonObject.get("title")
        val img_url = jsonObject.get("poster_path")
        holder?.tvAnimalType?.text = title as CharSequence?
        Picasso.get().load(IMG_URL.plus(img_url)).into(holder?.imgItem);
        holder?.itemView.setOnClickListener {
            cellClickListener.onCellClickListener(movieId)
        }
    }

}


class LoadingView(view: View) : RecyclerView.ViewHolder(view) {
    val progressBar = view.progressBar
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvAnimalType = view.tv_animal_type
    val imgItem = view.img_item

}