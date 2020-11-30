package app.utesting.android.unittestingdemoapp.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.utesting.android.unittestingdemoapp.getOrAwaitValue
import app.utesting.android.unittestingdemoapp.launchFragmentInHiltContainer
import app.utesting.android.unittestingdemoapp.ui.ShoppingFragment
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by murodjon on 2020/11/26
 */
/**
 * AndroidJUnit4 does mean, in general JUnit is used to test Java or kotlin code, any code runs on JVM, but here we are not such a plain java
 * or kotlin environment and we're inside of android environment. That's a difference between JVM, our tests run on android emulator not in JVM.
 * They're instrumented Unit tests and they need android components.
 * @SmallTest , MediumTest, LargeTest
 */
@ExperimentalCoroutinesApi
//@RunWith(AndroidJUnit4::class)
@SmallTest
@HiltAndroidTest
class ShoppingDaoTest {

    lateinit var dao: ShoppingDao

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    /**
     * A JUnit Test rule that swaps the background executor used by the Architecture
     * Components with a different one which executes each task synchronously
     */
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: ShoppingItemDatabase

    @Before
    fun setup(){
        hiltRule.inject()
        dao = database.shoppingDao()
    }

    @After
    fun close(){
        database.close()
    }

    @Test
    fun testLaunchFragmentInHiltContainer(){
        /**
         * Make sure the device you are running the tests on is unlocked.
         * If the screen is off or at the lock screen you will get a stack trace that looks roughly like this:
         */
        launchFragmentInHiltContainer<ShoppingFragment> {

        }
    }

    @Test
    fun insertShoppingItem() = runBlockingTest {
        val shoppingItem = ShoppingItem("name", 1, 1f, "url", id = 1)
        dao.insertShoppingItem(shoppingItem)

        val allShoppingItems = dao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(allShoppingItems).contains(shoppingItem)
    }

    @Test
    fun deleteShoppingItem() = runBlockingTest {
        val shoppingItem = ShoppingItem("name", 1, 1f, "url", id = 1)
        dao.insertShoppingItem(shoppingItem)
        dao.deleteShoppingItem(shoppingItem)

        val allShoppingItems = dao.observeAllShoppingItems().getOrAwaitValue()
        assertThat(allShoppingItems).doesNotContain(shoppingItem)
    }

    @Test
    fun observerveTotalPriceSum() = runBlockingTest {
        val shoppingItem1 = ShoppingItem("name 1", 1, 10f, "url 1", id = 1)
        val shoppingItem2 = ShoppingItem("name 2", 4, 2.4f, "url 2", id = 2)
        val shoppingItem3 = ShoppingItem("name 3", 10, 4.6f, "url 3", id = 3)

        dao.insertShoppingItem(shoppingItem1)
        dao.insertShoppingItem(shoppingItem2)
        dao.insertShoppingItem(shoppingItem3)
        val totalPrice = dao.observeTotalPrice().getOrAwaitValue()
        assertThat(totalPrice).isEqualTo(1*10f+4*2.4f+10*4.6f)
    }

}