package app.utesting.android.unittestingdemoapp.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import app.utesting.android.unittestingdemoapp.getOrAwaitValue
import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

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
@RunWith(AndroidJUnit4::class)
@SmallTest
class ShoppingDaoTest {

    private lateinit var database: ShoppingItemDatabase
    private lateinit var dao: ShoppingDao

    /**
     * A JUnit Test rule that swaps the background executor used by the Architecture
     * Components with a different one which executes each task synchronously
     */
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup(){
        database = Room
            .inMemoryDatabaseBuilder(ApplicationProvider.getApplicationContext(), ShoppingItemDatabase::class.java)
            .allowMainThreadQueries()// in Unit tests try to work on thee same thread, since multithreading manipulates each other
            .build()
        dao = database.shoppingDao()
    }

    @After
    fun close(){
        database.close()
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