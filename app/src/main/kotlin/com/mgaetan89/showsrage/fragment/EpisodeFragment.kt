package com.mgaetan89.showsrage.fragment

import android.os.Bundle
import com.mgaetan89.showsrage.Constants
import com.mgaetan89.showsrage.R
import com.mgaetan89.showsrage.activity.MainActivity
import com.mgaetan89.showsrage.adapter.EpisodePagerAdapter
import com.mgaetan89.showsrage.extension.getEpisodeSort
import com.mgaetan89.showsrage.extension.getPreferences
import com.mgaetan89.showsrage.model.Sort

class EpisodeFragment : TabbedFragment() {
	private val episodes = mutableListOf<Int>()

	override fun onActivityCreated(savedInstanceState: Bundle?) {
		super.onActivityCreated(savedInstanceState)

		val activity = this.activity
		val arguments = this.arguments

		if (activity is MainActivity) {
			activity.displayHomeAsUp(true)
			activity.setTitle(R.string.episode)
		}

		if (arguments != null) {
			val episodeNumber = arguments.getInt(Constants.Bundle.EPISODE_NUMBER)
			val episodesCount = arguments.getInt(Constants.Bundle.EPISODES_COUNT)

			for (i in episodesCount downTo 1) {
				this.episodes.add(i)
			}

			val ascendingOrder = Sort.ASCENDING == activity?.getPreferences()?.getEpisodeSort()

			if (ascendingOrder) {
				this.episodes.reverse()
			}

			this.updateState(false)
			this.selectTab(if (ascendingOrder) episodeNumber - 1 else episodesCount - episodeNumber)
		}
	}

	override fun onDestroy() {
		this.episodes.clear()

		super.onDestroy()
	}

	override fun getAdapter() = EpisodePagerAdapter(this.childFragmentManager, this, this.episodes)

	override fun useSwipeToRefresh() = false

	companion object {
		fun newInstance(episodeId: String, episodeNumber: Int, episodesCount: Int, seasonNumber: Int, indexerId: Int) = EpisodeFragment().apply {
			this.arguments = Bundle().apply {
				this.putString(Constants.Bundle.EPISODE_ID, episodeId)
				this.putInt(Constants.Bundle.EPISODE_NUMBER, episodeNumber)
				this.putInt(Constants.Bundle.EPISODES_COUNT, episodesCount)
				this.putInt(Constants.Bundle.SEASON_NUMBER, seasonNumber)
				this.putInt(Constants.Bundle.INDEXER_ID, indexerId)
			}
		}
	}
}
