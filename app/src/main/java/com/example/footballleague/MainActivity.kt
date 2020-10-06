package com.example.footballleague

import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MainActivity : AppCompatActivity() {
    private var footballItem: MutableList<FootBall> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        MainActivityUi().setContentView(this)
        showRecylerView()
    }

    class MainActivityUi : AnkoComponent<MainActivity> {
        override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
            verticalLayout {
                lparams(matchParent, matchParent)
                orientation = LinearLayout.VERTICAL

                recyclerView {
                    id = R.id.recyclerView
                    lparams(matchParent, wrapContent)
                }
            }
        }
    }

    class FootballUi : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {
            linearLayout {
                orientation = LinearLayout.VERTICAL
                lparams(matchParent, wrapContent)
                padding = dip(16)

                imageView {
                    id = R.id.league_badge_id
                    imageResource = R.drawable.english_premier_league
                    this@linearLayout.gravity = Gravity.CENTER_HORIZONTAL
                }.lparams(dip(100), dip(100))

                textView {
                    id = R.id.league_name_id
                    text = resources.getString(R.string.hint_name)
                    gravity = Gravity.CENTER_HORIZONTAL
                }.lparams(matchParent, wrapContent) {
                    margin = dip(10)
                    gravity = Gravity.CENTER_VERTICAL
                }
            }
        }
    }

    private fun initData() {
        val name = resources.getStringArray(R.array.league_name)
        val image = resources.obtainTypedArray(R.array.league_badge)
        val description = resources.getStringArray(R.array.league_description)
        footballItem.clear()
        for (i in name.indices) {
            footballItem.add(FootBall(name[i], image.getResourceId(i, 0), description[i]))
        }
        image.recycle()
    }

    private fun showRecylerView() {
        findViewById<RecyclerView>(R.id.recyclerView).layoutManager = GridLayoutManager(this, 2)
        findViewById<RecyclerView>(R.id.recyclerView).adapter = FootballAdapter(footballItem) {
            startActivity<DetailActivity>(DetailActivity.POSITION_EXTRA to it)
        }
    }
}
