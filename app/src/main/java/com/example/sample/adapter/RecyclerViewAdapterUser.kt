package com.example.sample.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.sample.AlbumActivity
import com.example.sample.BR
import com.example.sample.R
import com.example.sample.network.User

class RecyclerViewAdapterUser(list: List<User>) :
    RecyclerView.Adapter<RecyclerViewAdapterUser.ViewHolder>() {
    private val users: List<User> = list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapterUser.ViewHolder {
        val binding: ViewDataBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_user,
            parent,
            false)
        return RecyclerViewAdapterUser.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapterUser.ViewHolder, position: Int) {
        holder.bind(users[position])
        holder.itemView.setOnClickListener{
            Log.e("item on click","id ${users[position].id}")
            val context=holder.itemView.context
            val intent = Intent(context, AlbumActivity::class.java)
            intent.putExtra("albumId", users[position].id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return users.size
    }

    class ViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            Log.e("bind","user $user")
            binding.setVariable(BR.itemUser, user)
//            binding.setVariable(R.id.tv_id, user.id)
        }
    }
}

