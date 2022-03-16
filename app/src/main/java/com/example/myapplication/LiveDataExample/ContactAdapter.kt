package com.example.myapplication.LiveDataExample

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.databinding.ItemContactBinding

class ContactAdapter(private val context: Context, private val contacts: List<Contact>)
    : RecyclerView.Adapter<ContactAdapter.ViewHolder>() {

    companion object {
        val TAG: String = ContactAdapter::class.java.simpleName
    }

    // Usually involves inflating a layout from XML and returning the holder - THIS IS EXPENSIVE
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactAdapter.ViewHolder {
        Log.i(TAG, "onCreateViewHolder")
        val inflater = LayoutInflater.from(parent.context)
        val vbinding=ItemContactBinding.inflate(inflater, parent, false)
        return ViewHolder(vbinding)
    }

    // Returns the total count of items in the list
    override fun getItemCount() = contacts.size

    // Involves populating data into the item through holder - NOT expensive
    override fun onBindViewHolder(holder: ContactAdapter.ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder at position $position")
        val contact = contacts[position]
        holder.binding.txvName.text = contact.name
        holder.binding.tvAge.text= "Age: ${contact.age.toString()}"
        Glide.with(context).load(contact.imageUrl).into(holder.binding.ivProfile)

        //holder.setData(contact,position)
    }

    inner class ViewHolder(val binding: ItemContactBinding) : RecyclerView.ViewHolder(binding.root) {

    }
}