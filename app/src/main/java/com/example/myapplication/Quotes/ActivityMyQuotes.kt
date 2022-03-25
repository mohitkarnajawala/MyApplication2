package com.example.myapplication.Quotes

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMyQuotesRecycleViewBinding
import com.example.myapplication.recyclerview.RetrofitService.Companion.getInstance

class ActivityMyQuotes :AppCompatActivity() {

    lateinit var binding:ActivityMyQuotesRecycleViewBinding
    lateinit var quoteviewModel: QuotesViewModel

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMyQuotesRecycleViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.let {
            setTitle("Quotes")
        }

        val quotesApi=RetrofitHelper.getInstance().create(QuotesApi::class.java)

        val quotesRepository=QuotesRepository(quotesApi)

        quoteviewModel= ViewModelProvider(this,
            QuotesViewModelFactory(quotesRepository))
            .get(QuotesViewModel::class.java)

           //  val quoteList= mutableListOf<QuoteList>()


        val layoutManager = LinearLayoutManager(this)

        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.quotesRecyclerView.layoutManager = layoutManager

        quoteviewModel.quote.observe(this, Observer {

            Log.e("QUOTES:-",""+it)
            Log.e("QUOTES:-",""+it.results)
            //quoteList.add(it)
            val quotesAdapter=QuotesAdapter(this, it.results )
            binding.quotesRecyclerView.adapter=quotesAdapter
            quotesAdapter.notifyDataSetChanged()
        })
    }
}