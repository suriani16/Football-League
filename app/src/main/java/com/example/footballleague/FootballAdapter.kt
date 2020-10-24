package com.example.footballleague

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.FootballLeague.R
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import org.jetbrains.anko.AnkoContext

class FootballAdapter(
    private var list: MutableList<FootBall>,
    private var listener: (FootBall) -> Unit
) : RecyclerView.Adapter<FootballAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MainActivity.FootballUi().createView(
                AnkoContext.Companion.create(
                    parent.context,
                    parent
                )
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position], listener)
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindItem(football: FootBall, listener: (FootBall) -> Unit) {
            itemView.findViewById<TextView>(R.id.league_name_id).text = football.leagueName
            football.leagueImage.let {
                Picasso.get().load(it).into(itemView.findViewById<ImageView>(R.id.league_badge_id))
            }
            itemView.setOnClickListener { listener(football) }
        }
    }
}