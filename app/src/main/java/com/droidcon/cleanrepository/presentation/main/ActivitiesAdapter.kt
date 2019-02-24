package com.droidcon.cleanrepository.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.droidcon.cleanrepository.R
import com.droidcon.data.UIFeedItem

class ActivitiesAdapter: ListAdapter<UIFeedItem, ActivityViewHolder>(ItemCallBack()) {

    class ItemCallBack : DiffUtil.ItemCallback<UIFeedItem>() {
        override fun areItemsTheSame(oldItem: UIFeedItem, newItem: UIFeedItem): Boolean { //TODO: Check this
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UIFeedItem, newItem: UIFeedItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActivityViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return ActivityViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActivityViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}