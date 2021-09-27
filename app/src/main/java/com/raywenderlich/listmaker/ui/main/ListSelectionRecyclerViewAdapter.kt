package com.raywenderlich.listmaker.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.listmaker.TaskList
import com.raywenderlich.listmaker.databinding.ListSelectionViewHolderBinding

class ListSelectionRecyclerViewAdapter(val lists: MutableList<TaskList>, val clickListener:
                                       ListSelectionRecyclerViewClickListener) :
    RecyclerView.Adapter<ListSelectionViewHolder>() {

    interface ListSelectionRecyclerViewClickListener {
        fun listItemClicked(list: TaskList)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListSelectionViewHolder {
     // creates a LayoutInflater and inflates it with the binding class allowing me to bind data to the view
     val binding = ListSelectionViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
     // creates and returns holder with the binding
     return ListSelectionViewHolder(binding)
    }

    //uses list to populate the ViewHolder
    override fun onBindViewHolder(holder: ListSelectionViewHolder,
                                  position: Int) {
        holder.binding.itemNumber.text = (position + 1).toString()
        holder.binding.itemString.text = lists[position].name
        holder.itemView.setOnClickListener {
            clickListener.listItemClicked(lists[position])
        }
        }

    override fun getItemCount(): Int {
        return lists.size
    }


    fun listsUpdated() {
        notifyItemInserted(lists.size-1)
    }
}