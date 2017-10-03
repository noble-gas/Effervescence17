package org.effervescence.app17.recyclerview.adapters

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.upcoming_event_layout.view.*
import org.effervescence.app17.R
import org.effervescence.app17.activities.EventDetailActivity
import org.effervescence.app17.models.Event
import org.effervescence.app17.utils.GlideApp
import org.jetbrains.anko.startActivity
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by betterclever on 17/09/17.
 */
class UpcomingAdapter(val context: Context) : RecyclerView.Adapter<UpcomingAdapter.ViewHolder>() {

    private val events = ArrayList<Event>()

    override fun getItemCount() = events.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(LayoutInflater.from(parent.context)
                    .inflate(R.layout.upcoming_event_layout, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(context, events[position])
    }

    fun addEvents(events: List<Event>) {
        this.events.addAll(events)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(context: Context, event: Event) {
            itemView.eventNameTV.text = event.name

            val date = Date(event.timestamp * 1000)
            val format = SimpleDateFormat("MMM dd HH:mm")
            format.timeZone = TimeZone.getTimeZone("Asia/India")
            val formatted = format.format(date)
            itemView.eventTime.text = formatted.toString()
            GlideApp.with(context)
                        .load(event.imageUrl)
                        .circleCrop()
                        .placeholder(R.drawable.ic_event)
                        .into(itemView.eventImageView)

            itemView.rootLayout.setOnClickListener({
                itemView.context.startActivity<EventDetailActivity>("id" to event.id)
            })

        }
    }
}