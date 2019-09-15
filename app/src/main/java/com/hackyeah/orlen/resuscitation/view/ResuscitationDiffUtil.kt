package com.hackyeah.orlen.resuscitation.view

import androidx.recyclerview.widget.DiffUtil
import com.hackyeah.orlen.resuscitation.repository.Resuscitation

object ResuscitationDiffUtil : DiffUtil.ItemCallback<Resuscitation>() {

    override fun areItemsTheSame(oldItem: Resuscitation, newItem: Resuscitation) = oldItem == newItem
    override fun areContentsTheSame(oldItem: Resuscitation, newItem: Resuscitation)
            = oldItem.text == newItem.text && oldItem.image == newItem.image
}