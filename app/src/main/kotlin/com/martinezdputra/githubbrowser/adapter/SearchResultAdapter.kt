package com.martinezdputra.githubbrowser.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.martinezdputra.githubbrowser.databinding.LayoutSearchUserItemBinding
import com.martinezdputra.githubbrowser.datamodel.UserDetailDataModel

class SearchResultAdapter(
    private val userDataModels: List<UserDetailDataModel>,
    private val onUserSelectedListener: OnUserSelectedListener)
    : RecyclerView.Adapter<SearchResultAdapter.SearchUserItemViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchUserItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding = LayoutSearchUserItemBinding.inflate(layoutInflater, parent, false)
        return SearchUserItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holderSearchUserItem: SearchUserItemViewHolder, position: Int) {
        val item = userDataModels[position]
        holderSearchUserItem.bind(item)
    }

    override fun getItemCount() = userDataModels.size

    interface OnUserSelectedListener {
        fun onUserSelected(selectedUserDataModel: UserDetailDataModel)
    }

    inner class SearchUserItemViewHolder(private val binding: LayoutSearchUserItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserDetailDataModel) {
            binding.run {
                viewModel = item
                executePendingBindings()

                layoutContainer.setOnClickListener {
                    onUserSelectedListener.onUserSelected(item)
                }
            }
        }
    }
}