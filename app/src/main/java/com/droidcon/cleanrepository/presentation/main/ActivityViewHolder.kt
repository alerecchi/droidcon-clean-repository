package com.droidcon.cleanrepository.presentation.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.droidcon.data.UIFeedItem
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.list_item.*

class ActivityViewHolder(override val containerView: View): RecyclerView.ViewHolder(containerView), LayoutContainer {

    fun bind(model: UIFeedItem) {
        Glide.with(itemView.context)
            .load(model.imageUrl)
            .into(avatar)

        //TODO: logo

        name.text = model.name
        activity.text = model.activity //TODO:change activity into something else
        date.text = model.date
    }
}