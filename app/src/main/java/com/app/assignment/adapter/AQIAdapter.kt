package com.app.assignment.adapter

import android.provider.Settings.Global.getString
import androidx.recyclerview.widget.ListAdapter
import javax.inject.Inject
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil

import androidx.recyclerview.widget.RecyclerView
import com.app.assignment.R
import com.app.assignment.databinding.ItemAqiBinding
import com.app.assignment.model.APIResponse
import com.app.assignment.utils.Constants
import io.ktor.client.engine.*
import kotlin.coroutines.coroutineContext

class AQIAdapter
@Inject
constructor() : ListAdapter<APIResponse, AQIAdapter.AQIViewHolder>(Diff) {

    class AQIViewHolder(private val binding: ItemAqiBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(Aqidata: APIResponse) {
            binding.apply {
                cityName.text = Aqidata.city
                currAQI.text =  "%.2f".format(Aqidata.aqi.toFloat())
                cityHighlights.text = itemView.context.getString(R.string.text_air_quality,Constants.getAQIHintHighlights(Aqidata.aqi))
                itemView.setBackgroundColor(itemView.context.resources.getColor(Constants.getAQIColorHighlights(Aqidata.aqi)))
            }
        }
    }

    object Diff : DiffUtil.ItemCallback<APIResponse>() {
        override fun areItemsTheSame(oldItem: APIResponse, newItem: APIResponse): Boolean {
            return oldItem.city == newItem.city
        }

        override fun areContentsTheSame(oldItem: APIResponse, newItem: APIResponse): Boolean {
            return oldItem.equals(newItem)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AQIViewHolder {
        return AQIViewHolder(
            ItemAqiBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }


    override fun onBindViewHolder(holder: AQIViewHolder, position: Int) {
        val post = getItem(position)
        if (post != null) {
            holder.bind(post)
        }
    }
}