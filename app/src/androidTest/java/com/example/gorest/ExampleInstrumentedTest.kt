package com.example.gorest

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.gorest.presentation.MockResponseFileReader
import junit.framework.Assert

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.gorest", appContext.packageName)
    }

    @Test
    fun readSuccessJson(){
        val reader = MockResponseFileReader("success_response.json")
        Assert.assertNotNull(reader.content)
    }
}