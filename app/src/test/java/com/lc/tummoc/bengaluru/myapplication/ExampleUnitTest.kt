package com.lc.tummoc.bengaluru.myapplication

import android.os.Build
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@Config(sdk = [Build.VERSION_CODES.P])
@RunWith(RobolectricTestRunner::class)
class ExampleUnitTest {

    private lateinit var activity: Nav
    private lateinit var scenario: FragmentScenario<Frag1>

    @Before
    fun setup() {
        val controllerViewLocalsActivity =
            Robolectric.buildActivity(Nav::class.java).setup()
        activity = controllerViewLocalsActivity.get()
        scenario = launchFragmentInContainer {
            Frag1.newInstance(activity)
        }
    }

    @Test
    fun addition_isCorrect() {
        activity.abc.abc.observe(activity) {
            println("Value $it")
        }

        scenario.onFragment {
            //Navigation.setViewNavController(it.requireView(), activity.navController)
            it.binding.wew2.performClick()
        }

        assertEquals(2, activity.abc.abc.value)
    }
}