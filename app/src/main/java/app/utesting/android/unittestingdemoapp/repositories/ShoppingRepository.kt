package app.utesting.android.unittestingdemoapp.repositories

import androidx.lifecycle.LiveData
import app.utesting.android.unittestingdemoapp.data.local.ShoppingItem
import app.utesting.android.unittestingdemoapp.data.remote.responses.ImageResponse
import app.utesting.android.unittestingdemoapp.other.Resource
import retrofit2.Response

/**
 * Created by murodjon on 2020/11/26
 */
interface ShoppingRepository {
    suspend fun insertShoppingItem(shoppingItem: ShoppingItem)

    suspend fun deleteShoppingItem(shoppingItem: ShoppingItem)

    fun observeAllShoppingItems():LiveData<List<ShoppingItem>>

    fun observeTotalPrice():LiveData<Float>

    suspend fun searchForImage(imageQuery:String):Resource<ImageResponse>
}