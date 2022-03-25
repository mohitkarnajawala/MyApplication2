package com.example.myapplication.Quotes

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ItemQuotesBinding

class QuotesAdapter(val context:Context , private val quoteList: List<Result>):
    RecyclerView.Adapter<QuotesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding : ItemQuotesBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val vBinding= ItemQuotesBinding.inflate(inflater,parent,false)
        return ViewHolder(vBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val quote=quoteList[position]
        holder.binding.tvQoutes.text=quote.content
        holder.binding.tvAuther.text="- ${quote.author}"

       // Log.e("Position:-",""+position)
       // Log.e("Content:-",""+quote.results[position].content)

    }

    override fun getItemCount(): Int {
        return quoteList.size
    }
}