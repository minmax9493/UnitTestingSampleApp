package app.utesting.android.unittestingdemoapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import app.utesting.android.unittestingdemoapp.R

/**
 * Created by murodjon on 2020/11/28
 */
class ShoppingFragment : Fragment(R.layout.fragment_shopping) {

    lateinit var viewModel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ShoppingViewModel::class.java)
    }
}