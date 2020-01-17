package com.mp.dailynews.presentation

import android.os.Build
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import android.R
import com.mp.dailynews.presentation.activities.ArticleWebActivity
import org.robolectric.Shadows.shadowOf

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class ArticleWebActivityTest{

    var activity: ArticleWebActivity? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        activity = Robolectric.buildActivity(ArticleWebActivity::class.java)
            .create()
            .resume()
            .get()
    }

    @Test
    fun shouldNotBeNull() {
        assertNotNull(activity)
    }

    @Test
    fun shouldCloseActivity() {
        shadowOf(activity).clickMenuItem(R.id.home)
        assertTrue(shadowOf(activity).isFinishing())
    }
}