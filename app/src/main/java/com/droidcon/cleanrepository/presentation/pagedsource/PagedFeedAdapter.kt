package com.droidcon.cleanrepository.presentation.pagedsource

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.droidcon.cleanrepository.R
import com.droidcon.cleanrepository.model.UIFeedItem
import com.droidcon.cleanrepository.presentation.base.FeedViewHolder

class PagedFeedAdapter : PagedListAdapter<UIFeedItem, FeedViewHolder>(ItemCallBack()) {

    class ItemCallBack : DiffUtil.ItemCallback<UIFeedItem>() {
        override fun areItemsTheSame(oldItem: UIFeedItem, newItem: UIFeedItem): Boolean {
            return oldItem.id == newItem.id
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
        getItem(position)?.let { holder.bind(it) }
    }
}