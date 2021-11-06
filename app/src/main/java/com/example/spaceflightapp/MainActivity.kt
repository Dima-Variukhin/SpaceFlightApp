package com.example.spaceflightapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.example.spaceflightapp.core.SpaceFlightApp
import com.example.spaceflightapp.presentation.articles.BaseFragment

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    fun <T : ViewModel> getViewModel(model: Class<T>, owner: ViewModelStoreOwner) =
        (application as SpaceFlightApp).viewModel(model, owner)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = getViewModel(MainViewModel::class.java, this)

        viewModel.observe(this) {
            navigate(viewModel.getFragment(it))
        }
        viewModel.init()

    }

    private fun navigate(fragment: BaseFragment<*>) = with(supportFragmentManager) {
        beginTransaction()
            .replace(R.id.container, fragment, fragment.name())
            .commit()
    }
}