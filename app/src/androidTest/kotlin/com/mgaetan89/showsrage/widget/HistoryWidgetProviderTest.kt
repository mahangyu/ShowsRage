package com.mgaetan89.showsrage.widget

import android.appwidget.AppWidgetManager
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.mgaetan89.showsrage.R
import com.mgaetan89.showsrage.TestActivity
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HistoryWidgetProviderTest {
	@JvmField
	@Rule
	val activityRule = ActivityTestRule(TestActivity::class.java, false, true)

	private lateinit var provider: HistoryWidgetProvider

	@Before
	fun before() {
		this.provider = HistoryWidgetProvider()
	}

	@Test
	fun getListAdapterIntent() {
		val intent = this.provider.getListAdapterIntent(this.activityRule.activity, 42)

		assertThat(intent).isNotNull()
		assertThat(intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID)).isEqualTo(42)
		assertThat(intent.component.className).isEqualTo(HistoryWidgetService::class.java.name)
	}

	@Test
	fun getListAdapterIntentNoContext() {
		val intent = this.provider.getListAdapterIntent(null, 42)

		assertThat(intent).isNotNull()
		assertThat(intent.extras).isNull()
		assertThat(intent.component).isNull()
	}

	@Test
	fun getWidgetEmptyText() {
		assertThat(this.provider.getWidgetEmptyText(this.activityRule.activity))
				.isEqualTo(this.activityRule.activity.getString(R.string.no_history))
	}

	@Test
	fun getWidgetEmptyTextNoContext() {
		assertThat(this.provider.getWidgetEmptyText(null)).isNull()
	}

	@Test
	fun getWidgetTitle() {
		assertThat(this.provider.getWidgetTitle(this.activityRule.activity))
				.isEqualTo(this.activityRule.activity.getString(R.string.history))
	}

	@Test
	fun getWidgetTitleNoContext() {
		assertThat(this.provider.getWidgetTitle(null)).isNull()
	}
}
