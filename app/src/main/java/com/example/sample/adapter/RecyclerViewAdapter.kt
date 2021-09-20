package com.example.sample.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.sample.BR
import com.example.sample.R
import com.example.sample.network.Photo
import com.example.sample.view.AlbumActivity
import com.example.sample.view.PhotoActivity

class RecyclerViewAdapter(list: List<Photo>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    private val photos: List<Photo> = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_photo,
            parent,
            false)
        return RecyclerViewAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bind(photos[position])

        holder.itemView.setOnClickListener{
            Log.e("item on click","id ${photos[position].id}")
            val context=holder.itemView.context
            val intent = Intent(context, PhotoActivity::class.java)
            intent.putExtra("photo", photos[position])
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return photos.size
    }

    class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo) {
            Log.e("bind photo"," $photo")
            binding.setVariable(BR.itemPhoto, photo)
        }
    }
}

