package com.rviannaoliveira.introducaoandroid

import android.content.Intent
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.test.uiautomator.UiDevice
import android.view.View
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private var texto: String? = null

    @get:Rule
    private val mainActivityActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        texto = "Hello World!"
        mainActivityActivityTestRule.launchActivity(Intent())

    }

    @Test
    @Throws(Exception::class)
    fun preenchendoCampoVazio_setandoCampoTexto_verificaValor() {
        onView(withId(R.id.nome)).perform(typeText(texto), closeSoftKeyboard())
        onView(withId(R.id.btn_ok)).perform(click())
        onView(withId(R.id.txt_nome)).check(matches(withText(texto)))
    }

    @Test
    @Throws(Exception::class)
    fun preenchendoCampoVazio_setandoCampoTexto_verificaValor_QuandoGiraTela() {
        onView(withId(R.id.nome)).perform(typeText(texto), closeSoftKeyboard())
        onView(withId(R.id.btn_ok)).perform(click())
        onView(withId(R.id.txt_nome)).check(matches(withText(texto)))

        val device = UiDevice.getInstance(getInstrumentation())
        device.setOrientationRight()
        onView(withId(R.id.txt_nome)).check(matches(not<View>(withText("Exemplo"))))
        onView(withId(R.id.txt_nome)).check(matches(withText(texto)))
    }
}
