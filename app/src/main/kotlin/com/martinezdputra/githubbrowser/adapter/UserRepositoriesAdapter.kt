package com.martinezdputra.githubbrowser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.martinezdputra.githubbrowser.databinding.LayoutUserRepositoriesItemBinding
import com.martinezdputra.githubbrowser.datamodel.UserRepositoryDataModel

class UserRepositoriesAdapter(
    private val userRepositories: List<UserRepositoryDataModel>)
    : RecyclerView.Adapter<UserRepositoriesAdapter.UserRepositoriesItemViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserRepositoriesItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = LayoutUserRepositoriesItemBinding.inflate(layoutInflater, parent, false)
        return UserRepositoriesItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: UserRepositoriesItemViewHolder, position: Int) {
        val item = userRepositories[position]
        holder.bind(item)
    }

    override fun getItemCount() = userRepositories.size

    inner class UserRepositoriesItemViewHolder(private val binding: LayoutUserRepositoriesItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: UserRepositoryDataModel) {
            binding.run {
                viewModel = item
                executePendingBindings()
            }
        }
    }
}