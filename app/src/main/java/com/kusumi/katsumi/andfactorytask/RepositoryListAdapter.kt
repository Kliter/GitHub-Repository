package com.kusumi.katsumi.andfactorytask

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.snippet_repository_list_item.view.*

class RepositoryListAdapter(
    private val itemClickListener: ItemClickListener,
    private val repositoryList: MutableList<Repository>
): RecyclerView.Adapter<RepositoryListAdapter.ViewHolder>() {


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var repositoryName: TextView = view.tvRepositoryName
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
        val view: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.snippet_repository_list_item, parent, false)

        view.setOnClickListener {
            itemClickListener.onItemClick(view, mRecyclerView!!.getChildAdapterPosition(view))
        }

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return repositoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository: Repository = repositoryList[position]
        holder.repositoryName.text = repository.repositoryName
    }
}