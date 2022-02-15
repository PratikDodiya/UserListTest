package com.pd.userlisttest.ui.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.pd.userlisttest.R
import com.pd.userlisttest.data.entities.User
import com.pd.userlisttest.databinding.ItemUserBinding

class UserListAdapter : RecyclerView.Adapter<UserListViewHolder>() {

    private val items = ArrayList<User>()

    fun setItems(items: ArrayList<User>) {
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val binding: ItemUserBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_user,
            parent,
            false
        )
        return UserListViewHolder(binding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) = holder.bind(items[position])
}

class UserListViewHolder(private val itemBinding: ItemUserBinding) :
    RecyclerView.ViewHolder(itemBinding.root),
    View.OnClickListener {

    private lateinit var user: User

    init {
        itemBinding.root.setOnClickListener(this)
    }

    fun bind(item: User) {
        itemBinding.data = item
        itemBinding.executePendingBindings()
    }

    override fun onClick(view: View?) {

    }
}