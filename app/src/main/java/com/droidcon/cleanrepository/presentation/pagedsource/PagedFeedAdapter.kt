package com.droidcon.cleanrepository.presentation.pagedsource

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.droidcon.cleanrepository.R
import com.droidcon.cleanrepository.domain.model.Feed
import com.droidcon.cleanrepository.mapper.asUIModel
import com.droidcon.cleanrepository.presentation.base.FeedViewHolder

class PagedFeedAdapter : PagedListAdapter<Feed, FeedViewHolder>(ItemCallBack()) {

    class ItemCallBack : DiffUtil.ItemCallback<Feed>() {
        override fun areItemsTheSame(oldItem: Feed, newItem: Feed): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Feed, newItem: Feed): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return FeedViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bind(getItem(position)!!.asUIModel())
    }
}