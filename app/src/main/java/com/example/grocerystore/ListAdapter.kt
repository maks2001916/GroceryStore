package com.example.grocerystore

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView

class ListAdapter(context: Context, groceryList: MutableList<Grocery>) :
ArrayAdapter<Grocery>(context, R.layout.list_item, groceryList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        val grocery = getItem(position)
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false)
        }
        val imageViewIV = view?.findViewById<ImageView>(R.id.imageIV)
        val titleTV = view?.findViewById<TextView>(R.id.titleTV)
        val priceTV = view?.findViewById<TextView>(R.id.priceTV)

        imageViewIV?.setImageBitmap(grocery?.image)
        titleTV?.text = grocery?.title
        priceTV?.text = grocery?.price

        return view!!
    }
}