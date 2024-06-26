package com.example.fotografpaylasmafirebase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fotografpaylasmafirebase.R

import com.example.fotografpaylasmafirebase.model.Post
import com.squareup.picasso.Picasso

class HaberRecyclerAdapter(val postList:ArrayList<Post>) : RecyclerView.Adapter<HaberRecyclerAdapter.PostHolder>(){

    class PostHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val emailTextView: TextView = itemView.findViewById(R.id.recycler_row_email)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
       val inflater=LayoutInflater.from(parent.context)
        val view=inflater.inflate(R.layout.recycler_row,parent,false)
        return PostHolder(view)
    }

    override fun getItemCount(): Int {
       return postList.size
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) {

    holder.itemView.findViewById<TextView>(R.id.recycler_row_email).text = postList[position].kullaniciEmail
    holder.itemView.findViewById<TextView>(R.id.recycler_row_aciklama).text=postList[position].kullaniciYorum


        Picasso.get().load(postList[position].gorselUrl).into(holder.itemView.findViewById<ImageView>(R.id.recycler_row_imageView))
    }
}