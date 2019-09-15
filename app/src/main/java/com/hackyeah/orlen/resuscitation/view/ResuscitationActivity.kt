package com.hackyeah.orlen.resuscitation.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.core.view.children
import androidx.lifecycle.Observer
import com.hackyeah.orlen.common.view.BaseActivity
import com.hackyeah.orlen.resuscitation.repository.ResuscitationScenario
import com.hackyeah.orlen.resuscitation.viewModel.ResuscitationViewModel
import kotlinx.android.synthetic.main.activity_resuscitation.*
import org.koin.android.viewmodel.ext.android.viewModel

class ResuscitationActivity : BaseActivity() {
    override val layoutRes = com.hackyeah.orlen.R.layout.activity_resuscitation
    override val viewModel: ResuscitationViewModel by viewModel()

    private val adapter: ResuscitationAdapter by lazy { ResuscitationAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        adult.setOnClickListener { selectResuscitationType(ResuscitationScenario.ADULT) }
        child.setOnClickListener { selectResuscitationType(ResuscitationScenario.CHILD) }
        baby.setOnClickListener { selectResuscitationType(ResuscitationScenario.BABY) }

        resuscitationRecyclerView.adapter = adapter

        viewModel.resuscitationSteps.observe(this, Observer {
            adapter.submitList(it)
        })

        viewModel.resuscitationLink.observe(this, Observer {movieLinkResource ->
            playMovie.setOnClickListener {
                val url = getString(movieLinkResource)
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)
            }
        })


    }

    private fun selectResuscitationType(resuscitationScenario: ResuscitationScenario) {
        viewModel.selectScenario(resuscitationScenario)
        selectionContainer.children.forEach { view ->
            view.visibility = View.INVISIBLE
        }

        Handler().postDelayed({
            selectionContainer
                .animate()
                .translationYBy((-selectionContainer.height + selectionContainer.height / 3).toFloat())
                .withEndAction {
                    resuscitationRecyclerView.visibility = View.VISIBLE
                    playMovie.visibility = View.VISIBLE
                }

        }, 200L)
    }
}
