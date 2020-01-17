package com.mp.dailynews.presentation

import android.os.Build
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import com.mp.dailynews.presentation.activities.ArticlesActivity

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class ArticlesActivityTest {

    var activity: ArticlesActivity? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        activity = Robolectric.buildActivity(ArticlesActivity::class.java)
            .create()
            .resume()
            .get()
    }

    @Test
    fun shouldNotBeNull() {
        assertNotNull(activity)
    }
}