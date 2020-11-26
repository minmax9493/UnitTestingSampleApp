package app.utesting.android.unittestingdemoapp.repositories

import androidx.lifecycle.LiveData
import app.utesting.android.unittestingdemoapp.data.local.ShoppingDao
import app.utesting.android.unittestingdemoapp.data.local.ShoppingItem
import app.utesting.android.unittestingdemoapp.data.remote.PixabayAPI
import app.utesting.android.unittestingdemoapp.data.remote.responses.ImageResponse
import app.utesting.android.unittestingdemoapp.other.Resource
import javax.inject.Inject

/**
 * Created by murodjon on 2020/11/26

 */
class DefaultShoppingRepository @Inject constructor(
    private val shoppingDao: ShoppingDao,
    private val pixelbayAPI:PixabayAPI):ShoppingRepository{

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.insertShoppingItem(shoppingItem)
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingDao.deleteShoppingItem(shoppingItem)
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.observeAllShoppingItems()
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return shoppingDao.observeTotalPrice()
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        TODO()
    }
}