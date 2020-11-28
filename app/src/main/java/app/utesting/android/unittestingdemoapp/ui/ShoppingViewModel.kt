package app.utesting.android.unittestingdemoapp.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.utesting.android.unittestingdemoapp.data.local.ShoppingItem
import app.utesting.android.unittestingdemoapp.data.remote.responses.ImageResponse
import app.utesting.android.unittestingdemoapp.other.Event
import app.utesting.android.unittestingdemoapp.other.Resource
import app.utesting.android.unittestingdemoapp.repositories.ShoppingRepository
import kotlinx.coroutines.launch

/**
 * Created by murodjon on 2020/11/28
 */
class ShoppingViewModel @ViewModelInject constructor(
        private val repository: ShoppingRepository
) : ViewModel() {

    val shoppingItems = repository.observeAllShoppingItems()

    val totalPrice = repository.observeTotalPrice()

    private val _images = MutableLiveData<Event<Resource<ImageResponse>>>()
    val images: LiveData<Event<Resource<ImageResponse>>> = _images

    private val _curImageUrl = MutableLiveData<String>()
    val curImageUrl: LiveData<String> = _curImageUrl

    private val _insertShoppingItemStatus = MutableLiveData<Event<Resource<ShoppingItem>>>()
    val insertShoppingItemStatus: LiveData<Event<Resource<ShoppingItem>>> = _insertShoppingItemStatus

    fun setCurImageUrl(url: String) {
        _curImageUrl.postValue(url)
    }

    fun deleteShoppingItem(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.deleteShoppingItem(shoppingItem)
    }

    fun insertShoppingItemIntoDb(shoppingItem: ShoppingItem) = viewModelScope.launch {
        repository.insertShoppingItem(shoppingItem)
    }

    fun insertShoppingItem(name: String, amountString: String, priceString: String) {

    }

    fun searchForImage(imageQuery: String) {

    }
}