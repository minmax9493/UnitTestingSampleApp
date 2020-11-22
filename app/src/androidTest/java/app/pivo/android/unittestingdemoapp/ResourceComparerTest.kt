package app.pivo.android.unittestingdemoapp

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

/**
 * Created by murodjon on 2020/11/22
 */
class ResourceComparerTest{

    private lateinit var resourceComparer: ResourceComparer

    @Before
    fun setup(){
        resourceComparer = ResourceComparer()
    }

    @Test
    fun stringResourceSameAsGivenString_returnFalse(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.stringResourceIsSameAsGivenStringReturnsTrue(context, R.string.app_name, "Hello World")
        assertThat(result).isFalse()
    }

    @Test
    fun stringResourceSameAsGivenString_returnTrue(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        val result = resourceComparer.stringResourceIsSameAsGivenStringReturnsTrue(context, R.string.app_name, "UnitTestingDemoApp")
        assertThat(result).isTrue()
    }

}