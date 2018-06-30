package com.mgaetan89.showsrage.extension

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class StringExtension_ToTimestampTest {
    @Test
    fun toTimestamp() {
        assertThat("".toTimestamp("")).isEqualTo(0L)
        assertThat("".toTimestamp("yyyy-MM-dd")).isEqualTo(0L)
        assertThat("02:34:56".toTimestamp("")).isEqualTo(0L)
        assertThat("02:34:56".toTimestamp("yyyy-MM-dd")).isEqualTo(0L)
        assertThat("30.06.2018".toTimestamp("")).isEqualTo(0L)
        assertThat("30.06.2018".toTimestamp("yyyy-MM-dd")).isEqualTo(0L)
        assertThat("2018-06-30".toTimestamp("")).isEqualTo(0L)
        assertThat("2018-06-30".toTimestamp("yyyy-MM-dd")).isEqualTo(1530309600000L)
        assertThat("2018-06-30 02:34:56".toTimestamp("")).isEqualTo(0L)
        assertThat("2018-06-30 02:34:56".toTimestamp("yyyy-MM-dd")).isEqualTo(1530309600000L)
    }
}
