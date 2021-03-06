package com.mgaetan89.showsrage.network

import android.content.SharedPreferences
import com.mgaetan89.showsrage.extension.getApiKey
import com.mgaetan89.showsrage.extension.getPortNumber
import com.mgaetan89.showsrage.extension.getServerAddress
import com.mgaetan89.showsrage.extension.getServerPath
import com.mgaetan89.showsrage.extension.useHttps
import com.mgaetan89.showsrage.model.ImageType
import com.mgaetan89.showsrage.model.Indexer
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@RunWith(Parameterized::class)
class SickRageApi_GetPosterUrlTest(
		val useHttps: Boolean, val address: String, val port: String, val path: String,
		val apiKey: String, val indexerId: Int, val indexer: Indexer?, val url: String
) {
	@Before
	fun before() {
		val preferences = mock(SharedPreferences::class.java)
		`when`(preferences.useHttps()).thenReturn(this.useHttps)
		`when`(preferences.getServerAddress()).thenReturn(this.address)
		`when`(preferences.getPortNumber()).thenReturn(this.port)
		`when`(preferences.getServerPath()).thenReturn(this.path)
		`when`(preferences.getApiKey()).thenReturn(this.apiKey)

		SickRageApi.instance.init(preferences)
	}

	@Test
	fun getPosterUrl() {
		assertThat(SickRageApi.instance.getImageUrl(ImageType.POSTER, this.indexerId, this.indexer)).isEqualTo(this.url)
	}

	companion object {
		@JvmStatic
		@Parameterized.Parameters(name = "[{6}] {index} - {0}://{1}:{2}/{3}/{4}/")
		fun data(): Collection<Array<Any?>> {
			return listOf(
					arrayOf(false, "", "", "", "", 0, null, "http://127.0.0.1/?cmd=show.getposter"),
					arrayOf(false, "127.0.0.1", "", "", "", 123, null, "http://127.0.0.1/?cmd=show.getposter"),
					arrayOf(true, "", "", "", "", 0, null, "http://127.0.0.1/?cmd=show.getposter"),
					arrayOf(true, "127.0.0.1", "", "", "", 123, null, "https://127.0.0.1/?cmd=show.getposter"),

					// TVDB
					arrayOf<Any?>(false, "127.0.0.1", "8083", "", "", 0, Indexer.TVDB, "http://127.0.0.1:8083/?cmd=show.getposter&tvdbid=0"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "api", "", 123, Indexer.TVDB, "http://127.0.0.1:8083/api/?cmd=show.getposter&tvdbid=123"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "/api", "", 123, Indexer.TVDB, "http://127.0.0.1:8083/api/?cmd=show.getposter&tvdbid=123"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "api/", "", 123, Indexer.TVDB, "http://127.0.0.1:8083/api/?cmd=show.getposter&tvdbid=123"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "/api/", "", 123, Indexer.TVDB, "http://127.0.0.1:8083/api/?cmd=show.getposter&tvdbid=123"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "/api1/api2/", "", 123, Indexer.TVDB, "http://127.0.0.1:8083/api1/api2/?cmd=show.getposter&tvdbid=123"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "api", "apiKey", 123, Indexer.TVDB, "http://127.0.0.1:8083/api/apiKey/?cmd=show.getposter&tvdbid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "", "", 0, Indexer.TVDB, "https://127.0.0.1:8083/?cmd=show.getposter&tvdbid=0"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "api", "", 123, Indexer.TVDB, "https://127.0.0.1:8083/api/?cmd=show.getposter&tvdbid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "/api", "", 123, Indexer.TVDB, "https://127.0.0.1:8083/api/?cmd=show.getposter&tvdbid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "api/", "", 123, Indexer.TVDB, "https://127.0.0.1:8083/api/?cmd=show.getposter&tvdbid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "/api/", "", 123, Indexer.TVDB, "https://127.0.0.1:8083/api/?cmd=show.getposter&tvdbid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "/api1/api2/", "", 123, Indexer.TVDB, "https://127.0.0.1:8083/api1/api2/?cmd=show.getposter&tvdbid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "api", "apiKey", 123, Indexer.TVDB, "https://127.0.0.1:8083/api/apiKey/?cmd=show.getposter&tvdbid=123"),

					// TVRage
					arrayOf<Any?>(false, "127.0.0.1", "8083", "", "", 0, Indexer.TVRAGE, "http://127.0.0.1:8083/?cmd=show.getposter&tvrageid=0"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "api", "", 123, Indexer.TVRAGE, "http://127.0.0.1:8083/api/?cmd=show.getposter&tvrageid=123"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "/api", "", 123, Indexer.TVRAGE, "http://127.0.0.1:8083/api/?cmd=show.getposter&tvrageid=123"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "api/", "", 123, Indexer.TVRAGE, "http://127.0.0.1:8083/api/?cmd=show.getposter&tvrageid=123"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "/api/", "", 123, Indexer.TVRAGE, "http://127.0.0.1:8083/api/?cmd=show.getposter&tvrageid=123"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "/api1/api2/", "", 123, Indexer.TVRAGE, "http://127.0.0.1:8083/api1/api2/?cmd=show.getposter&tvrageid=123"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "api", "apiKey", 123, Indexer.TVRAGE, "http://127.0.0.1:8083/api/apiKey/?cmd=show.getposter&tvrageid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "", "", 0, Indexer.TVRAGE, "https://127.0.0.1:8083/?cmd=show.getposter&tvrageid=0"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "api", "", 123, Indexer.TVRAGE, "https://127.0.0.1:8083/api/?cmd=show.getposter&tvrageid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "/api", "", 123, Indexer.TVRAGE, "https://127.0.0.1:8083/api/?cmd=show.getposter&tvrageid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "api/", "", 123, Indexer.TVRAGE, "https://127.0.0.1:8083/api/?cmd=show.getposter&tvrageid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "/api/", "", 123, Indexer.TVRAGE, "https://127.0.0.1:8083/api/?cmd=show.getposter&tvrageid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "/api1/api2/", "", 123, Indexer.TVRAGE, "https://127.0.0.1:8083/api1/api2/?cmd=show.getposter&tvrageid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "api", "apiKey", 123, Indexer.TVRAGE, "https://127.0.0.1:8083/api/apiKey/?cmd=show.getposter&tvrageid=123"),

					// TVMaze
					arrayOf<Any?>(false, "127.0.0.1", "8083", "", "", 0, Indexer.TVMAZE, "http://127.0.0.1:8083/?cmd=show.getposter&tvmazeid=0"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "api", "", 123, Indexer.TVMAZE, "http://127.0.0.1:8083/api/?cmd=show.getposter&tvmazeid=123"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "/api", "", 123, Indexer.TVMAZE, "http://127.0.0.1:8083/api/?cmd=show.getposter&tvmazeid=123"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "api/", "", 123, Indexer.TVMAZE, "http://127.0.0.1:8083/api/?cmd=show.getposter&tvmazeid=123"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "/api/", "", 123, Indexer.TVMAZE, "http://127.0.0.1:8083/api/?cmd=show.getposter&tvmazeid=123"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "/api1/api2/", "", 123, Indexer.TVMAZE, "http://127.0.0.1:8083/api1/api2/?cmd=show.getposter&tvmazeid=123"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "api", "apiKey", 123, Indexer.TVMAZE, "http://127.0.0.1:8083/api/apiKey/?cmd=show.getposter&tvmazeid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "", "", 0, Indexer.TVMAZE, "https://127.0.0.1:8083/?cmd=show.getposter&tvmazeid=0"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "api", "", 123, Indexer.TVMAZE, "https://127.0.0.1:8083/api/?cmd=show.getposter&tvmazeid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "/api", "", 123, Indexer.TVMAZE, "https://127.0.0.1:8083/api/?cmd=show.getposter&tvmazeid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "api/", "", 123, Indexer.TVMAZE, "https://127.0.0.1:8083/api/?cmd=show.getposter&tvmazeid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "/api/", "", 123, Indexer.TVMAZE, "https://127.0.0.1:8083/api/?cmd=show.getposter&tvmazeid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "/api1/api2/", "", 123, Indexer.TVMAZE, "https://127.0.0.1:8083/api1/api2/?cmd=show.getposter&tvmazeid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "api", "apiKey", 123, Indexer.TVMAZE, "https://127.0.0.1:8083/api/apiKey/?cmd=show.getposter&tvmazeid=123"),

					// TMDB
					arrayOf<Any?>(false, "127.0.0.1", "8083", "", "", 0, Indexer.TMDB, "http://127.0.0.1:8083/?cmd=show.getposter&tmdbid=0"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "api", "", 123, Indexer.TMDB, "http://127.0.0.1:8083/api/?cmd=show.getposter&tmdbid=123"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "/api", "", 123, Indexer.TMDB, "http://127.0.0.1:8083/api/?cmd=show.getposter&tmdbid=123"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "api/", "", 123, Indexer.TMDB, "http://127.0.0.1:8083/api/?cmd=show.getposter&tmdbid=123"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "/api/", "", 123, Indexer.TMDB, "http://127.0.0.1:8083/api/?cmd=show.getposter&tmdbid=123"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "/api1/api2/", "", 123, Indexer.TMDB, "http://127.0.0.1:8083/api1/api2/?cmd=show.getposter&tmdbid=123"),
					arrayOf<Any?>(false, "127.0.0.1", "8083", "api", "apiKey", 123, Indexer.TMDB, "http://127.0.0.1:8083/api/apiKey/?cmd=show.getposter&tmdbid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "", "", 0, Indexer.TMDB, "https://127.0.0.1:8083/?cmd=show.getposter&tmdbid=0"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "api", "", 123, Indexer.TMDB, "https://127.0.0.1:8083/api/?cmd=show.getposter&tmdbid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "/api", "", 123, Indexer.TMDB, "https://127.0.0.1:8083/api/?cmd=show.getposter&tmdbid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "api/", "", 123, Indexer.TMDB, "https://127.0.0.1:8083/api/?cmd=show.getposter&tmdbid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "/api/", "", 123, Indexer.TMDB, "https://127.0.0.1:8083/api/?cmd=show.getposter&tmdbid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "/api1/api2/", "", 123, Indexer.TMDB, "https://127.0.0.1:8083/api1/api2/?cmd=show.getposter&tmdbid=123"),
					arrayOf<Any?>(true, "127.0.0.1", "8083", "api", "apiKey", 123, Indexer.TMDB, "https://127.0.0.1:8083/api/apiKey/?cmd=show.getposter&tmdbid=123")
			)
		}
	}
}
