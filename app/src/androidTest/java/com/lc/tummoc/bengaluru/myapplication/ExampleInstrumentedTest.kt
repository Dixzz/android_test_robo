package com.lc.tummoc.bengaluru.myapplication

import android.os.FileUtils
import android.os.UserManager
import androidx.core.os.UserManagerCompat
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.*

import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<Nav>()

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.lc.tummoc.bengaluru.myapplication", appContext.packageName)
    }

    @Test
    fun launch_activity() {
        activityScenarioRule.scenario.onActivity { activity ->
            val hostFragment = activity.supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment?
            println("Res1 ${activity.supportFragmentManager.fragments}")
            println("Res1 ${hostFragment?.childFragmentManager?.fragments} ${activity.navController.currentDestination}")
            println("Res1 ${hostFragment?.childFragmentManager?.fragments?.firstOrNull() as Frag1?} ${activity.navController.currentDestination}")
            //activity.goToFragOne()
            println("Res2 ${hostFragment?.childFragmentManager?.fragments} ${activity.navController.currentDestination}")
            println("Res2 ${hostFragment?.childFragmentManager?.fragments?.firstOrNull() as Frag2?} ${activity.navController.currentDestination}")
//            println("Res2 ${hostFragment?.childFragmentManager?.fragments} ${activity.navController.currentDestination}")
//            hostFragment?.childFragmentManager?.fragments?.forEach {
//                println("Res3 $it")
//            }

            assertNotNull(activity)
        }
    }

    @Test
    fun testNavigationToInGameScreen() {
        // Create a TestNavHostController
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())

        // Create a graphical FragmentScenario for the TitleScreen
        val titleScenario = launchFragmentInContainer<Frag1>()

        titleScenario.onFragment { fragment ->
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.test)

            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        // Verify that performing a click changes the NavController’s state
        onView(ViewMatchers.withId(R.id.wew2)).perform(ViewActions.click())

        val isFrag2 = navController.currentDestination?.id == R.id.fragment2

        val titleScenario2 = launchFragmentInContainer<Frag2>()
        titleScenario2.onFragment { fragment ->
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.test)

            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
        onView(ViewMatchers.withId(R.id.dadasd)).perform(ViewActions.click())
        val isFrag1 = navController.currentDestination?.id == R.id.fragment1

        assertEquals(true, isFrag1 && isFrag2)

        //assertThat(navController.currentDestination?.id).isEqualTo(R.id.in_game)
    }
}