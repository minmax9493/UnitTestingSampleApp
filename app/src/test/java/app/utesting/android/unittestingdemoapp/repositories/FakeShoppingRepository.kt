package app.utesting.android.unittestingdemoapp.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import app.utesting.android.unittestingdemoapp.data.local.ShoppingItem
import app.utesting.android.unittestingdemoapp.data.remote.responses.ImageResponse
import app.utesting.android.unittestingdemoapp.other.Resource

/**
 * Created by murodjon on 2020/11/28
 */
class FakeShoppingRepository : ShoppingRepository{

    private val shoppingItems = mutableListOf<ShoppingItem>()

    private val observableShoppingItems = MutableLiveData<List<ShoppingItem>>(shoppingItems)
    private val observableTotalPrice = MutableLiveData<Float>()

    private var shouldReturnNetworkError =false

    fun setShouldReturnNetworkError(value:Boolean){
        shouldReturnNetworkError = value
    }

    private fun refreshLiveData(){
        observableShoppingItems.postValue(shoppingItems)
        observableTotalPrice.postValue(getTotalPrice())
    }

    private fun getTotalPrice():Float{
        return shoppingItems.sumByDouble { it.price.toDouble() }.toFloat()
    }

    override suspend fun insertShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.add(shoppingItem)
        refreshLiveData()
    }

    override suspend fun deleteShoppingItem(shoppingItem: ShoppingItem) {
        shoppingItems.remove(shoppingItem)
        refreshLiveData()
    }

    override fun observeAllShoppingItems(): LiveData<List<ShoppingItem>> {
        return observableShoppingItems
    }

    override fun observeTotalPrice(): LiveData<Float> {
        return observableTotalPrice
    }

    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return if (shouldReturnNetworkError){
            Resource.error("Error", null)
        }else{
            Resource.success(ImageResponse(listOf(), 0, 0))
        }
    }
}