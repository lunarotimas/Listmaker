package com.raywenderlich.listmaker.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.listmaker.databinding.ListSelectionViewHolderBinding

class ListSelectionRecyclerViewAdapter : RecyclerView.Adapter<ListSelectionViewHolder>() {

    val listTitles = arrayOf("Shopping List", "Chores", "Android Tutorials")

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {

     // creates a LayoutInflater and inflates it with the binding class allowing me to bind data to the view

     val binding = ListSelectionViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
     // creates and returns holder with the binding

     return ListSelectionViewHolder(binding)
    }



    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
     holder.binding.itemNumber.text = (position + 1).toString()
     holder.binding.itemString.text = listTitles[position]
    }

    override fun getItemCount(): Int {
        return listTitles.size
    }
}