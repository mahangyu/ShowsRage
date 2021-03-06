package com.mgaetan89.showsrage.fragment

import com.mgaetan89.showsrage.R
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SettingsAboutShowsRageFragmentTest {
	@Test
	fun getTitleResourceId() {
		assertThat(SettingsAboutShowsRageFragment.newInstance().getTitleResourceId()).isEqualTo(R.string.app_name)
	}

	@Test
	fun getXmlResourceFile() {
		assertThat(SettingsAboutShowsRageFragment.newInstance().getXmlResourceFile()).isEqualTo(R.xml.settings_about_showsrage)
	}
}
