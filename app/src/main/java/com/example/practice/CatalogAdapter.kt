package com.example.practice

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.practice.retrofit.Catalog

class CatalogAdapter: RecyclerView.Adapter<CatalogAdapter.CatalogViewHolder>() {

    inner class CatalogViewHolder(view: View): RecyclerView.ViewHolder(view)

    private val callback = object: DiffUtil.ItemCallback<Catalog>(){
        override fun areItemsTheSame(oldItem: Catalog, newItem: Catalog): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Catalog, newItem: Catalog): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, callback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogViewHolder {
        return  CatalogViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_an_main, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CatalogViewHolder, position: Int) {
        val catalog = differ.currentList[position]
        holder.itemView.apply{
            findViewById<TextView>(R.id.catalog_name).text = catalog.name
            findViewById<TextView>(R.id.catalog_description).text = catalog.time_result
            findViewById<TextView>(R.id.catalog_price).text = catalog.price+ "₽"
        }
    }

    private var onItemClickListener: ((Catalog) -> Unit)?=null

    fun setOnItemClickListener(listener: (Catalog) -> Unit){
        onItemClickListener = listener
    }
}