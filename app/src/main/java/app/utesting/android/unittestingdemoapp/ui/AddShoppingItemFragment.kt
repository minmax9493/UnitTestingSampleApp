package app.utesting.android.unittestingdemoapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.utesting.android.unittestingdemoapp.R

/**
 * Created by murodjon on 2020/11/28
 */
class AddShoppingItemFragment : Fragment(R.layout.fragment_add_shopping_item) {

    lateinit var viewModel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
    }
}