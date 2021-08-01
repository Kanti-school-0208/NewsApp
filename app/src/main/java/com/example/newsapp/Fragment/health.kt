package com.example.newsapp.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.Newsgrp
import com.example.newsapp.R
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.interfaces.Newsservies
import retrofit2.Call
import retrofit2.Callback


class health : Fragment() {

    lateinit var hrecycler : RecyclerView
    lateinit var prog : ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val h = inflater.inflate(R.layout.fragment_health, container, false)
        hrecycler = h.findViewById(R.id.newsrecyclerhealth)
        prog = h.findViewById(R.id.hpb)
        val newsdata = Newsservies.newsinstance.getnews("in", "health", 2)
        newsdata.enqueue(object : Callback<Newsgrp> {
            override fun onResponse(call: Call<Newsgrp>, response: retrofit2.Response<Newsgrp>) {
                prog.visibility = View.GONE
                val listnews = response.body()!!.articles
                val adapter = NewsAdapter(requireContext(), listnews)
                hrecycler.adapter = adapter
                hrecycler.layoutManager = LinearLayoutManager(requireContext())

                Log.d("Data", listnews.toString())
//                newsarray.addAll(newslist)
                Log.d("Data", "Added")
            }

            override fun onFailure(call: Call<Newsgrp>, t: Throwable) {
                Log.d("Data", "Faild")
                Toast.makeText(requireContext(), t.message, Toast.LENGTH_LONG).show()
            }
        })
        return h
    }


}