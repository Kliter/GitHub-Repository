package com.kusumi.katsumi.andfactorytask.view

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kusumi.katsumi.andfactorytask.BR
import com.kusumi.katsumi.andfactorytask.R
import com.kusumi.katsumi.andfactorytask.model.GitHubService

class RepositoryListAdapter(
    private val onRepositoryItemClickListener: OnRepositoryItemClickListener,
    private var repositoryList: MutableList<GitHubService.Repository>
): RecyclerView.Adapter<RepositoryListAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var binding: ViewDataBinding? = null
        init {
            binding = DataBindingUtil.bind(view)
        }
    }

    interface OnRepositoryItemClickListener {
        fun onRepositoryItemClick(repository: GitHubService.Repository)
    }

    private var mRecyclerView: RecyclerView? = null

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        mRecyclerView = recyclerView
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        mRecyclerView = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.snippet_repository_list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Use data-binding.
        val repository: GitHubService.Repository = repositoryList[position]
        holder.binding?.setVariable(BR.repositoryData, repository)
        holder.binding?.executePendingBindings()
        holder.itemView.setOnClickListener {
            onRepositoryItemClickListener.onRepositoryItemClick(repository)
        }
    }

    fun setItemsAndRefresh(repositories: MutableList<GitHubService.Repository>?) {
        this.repositoryList = repositories!!
        notifyDataSetChanged()
    }
}