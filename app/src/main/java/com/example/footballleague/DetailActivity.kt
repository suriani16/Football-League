package com.example.footballleague

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class DetailActivity : AppCompatActivity() {
    companion object {
        const val POSITION_EXTRA: String = "position_extra"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = intent
        val list = intent.getParcelableExtra<FootBall>(POSITION_EXTRA)
        DetailActivityUi(list).setContentView(this)
    }

    class DetailActivityUi(private var list: FootBall) : AnkoComponent<DetailActivity> {
        override fun createView(ui: AnkoContext<DetailActivity>) = with(ui) {
            scrollView {
                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    lparams(matchParent, matchParent)

                    imageView {
                        Picasso.get().load(list.leagueImage).into(this)
                        id = R.id.league_badge_id
                        padding = dip(10)
                        this@linearLayout.gravity = Gravity.CENTER_HORIZONTAL
                    }.lparams(dip(80), dip(80))

                    textView {
                        id = R.id.league_name_id
                        text = list.leagueName
                        textSize = sp(10).toFloat()
                        gravity = Gravity.CENTER_HORIZONTAL
                        padding = dip(10)
                    }

                    textView {
                        id = R.id.league_desc_id
                        text = list.leagueDescription
                        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
                        padding = dip(10)
                    }
                }
            }
        }
    }
}