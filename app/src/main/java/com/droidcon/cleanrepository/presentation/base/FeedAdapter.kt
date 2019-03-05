package com.droidcon.cleanrepository.presentation.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.droidcon.cleanrepository.R
import com.droidcon.cleanrepository.model.UIFeedItem

class FeedAdapter : ListAdapter<UIFeedItem, FeedViewHolder>(ItemCallBack()) {

    class ItemCallBack : DiffUtil.ItemCallback<UIFeedItem>() {
        override fun areItemsTheSame(oldItem: UIFeedItem, newItem: UIFeedItem): Boolean { //TODO: Check this
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UIFeedItem, newItem: UIFeedItem): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return FeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}