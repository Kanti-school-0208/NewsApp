package com.example.newsapp.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.News
import com.example.newsapp.R
import kotlinx.android.synthetic.main.news_list.view.*

class NewsAdapter(val context: Context,val newslist: ArrayList<News>): RecyclerView.Adapter<NewsAdapter.newsviewholder>() {


    inner class newsviewholder(val newsview: View) : RecyclerView.ViewHolder(newsview){
        init{
            newsview.gotisite.setOnClickListener {
                val  url = newslist[adapterPosition].url
                val  builder =  CustomTabsIntent.Builder()
                val  customTabsIntent = builder.build()
                customTabsIntent.launchUrl(context, Uri.parse(url))
            }
            newsview.sharebtn.setOnClickListener {
                val  url = newslist[adapterPosition].url
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT,"$url")
                val chooser = Intent.createChooser(intent,"Share via...")
                context.startActivity(chooser)

            }
            newsview.seemore.setOnClickListener {
                newsview.content.visibility = View.VISIBLE
                newsview.seeless.visibility = View.VISIBLE
                newsview.seemore.visibility = View.GONE
            }
            newsview.seeless.setOnClickListener {
                newsview.content.visibility = View.GONE
                newsview.seeless.visibility = View.GONE
                newsview.seemore.visibility = View.VISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsviewholder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.news_list,parent,false)
        return newsviewholder(view)
    }

    override fun onBindViewHolder(holder: newsviewholder, position: Int) {
        val news1 = newslist[position]
        holder.newsview.newstitle.text = news1.title
        holder.newsview.content.text = news1.description
        Glide.with(context).load(news1.urlToImage).into(holder.newsview.newsimg)
    }

    override fun getItemCount(): Int {
        return newslist.size
    }

}
