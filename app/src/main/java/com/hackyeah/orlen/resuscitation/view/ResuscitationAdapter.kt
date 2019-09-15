package com.hackyeah.orlen.resuscitation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hackyeah.orlen.R
import com.hackyeah.orlen.resuscitation.repository.Resuscitation
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.resuscitation_list_item.*

class ResuscitationAdapter : ListAdapter<Resuscitation, ResuscitationAdapter.ResuscitationViewHolder>(ResuscitationDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResuscitationViewHolder {
        return ResuscitationViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.resuscitation_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ResuscitationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ResuscitationViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(item: Resuscitation) {
            step.text = item.stepCount.toString()
            image.setImageResource(item.image)
            text.setText(item.text)
        }
    }
}