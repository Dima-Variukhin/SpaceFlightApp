package com.myapp.spaceflightapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelStoreOwner
import com.myapp.spaceflightapp.core.SpaceFlightApp
import com.myapp.spaceflightapp.presentation.BaseFragment
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private val web = WebViewInterface.Base()
    fun <T : ViewModel> getViewModel(model: Class<T>, owner: ViewModelStoreOwner) =
        (application as SpaceFlightApp).viewModel(model, owner)

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        viewModel = getViewModel(MainViewModel::class.java, this)
        web.initSettings(findViewById(R.id.rootView), this)

        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val tabChosen: (Int) -> Unit = { position ->
            viewModel.save(position)
        }
        tabLayout.addOnTabSelectedListener(TabListener(tabChosen))
        viewModel.observe(this) {
            if (it != 4) {
                tabLayout.getTabAt(it)?.select()
                navigate(viewModel.getFragment(it))
            } else {
                intent = Intent(this, SearchActivity::class.java)
                startActivity(intent)
            }
        }
        viewModel.observeWeb(this) {
            web.loadWebView(it, findViewById(R.id.rootView), this)
        }
        viewModel.observeShare(this) {
            val intent = Intent(Intent.ACTION_SEND).apply {
                putExtra(Intent.EXTRA_TEXT, it)
                type = "text/plain"
            }
            startActivity(intent)
        }
        viewModel.init()
    }

    private fun navigate(fragment: BaseFragment<*>) = with(supportFragmentManager) {
        beginTransaction()
            .replace(R.id.container, fragment, fragment.name())
            .commit()
    }

    override fun onBackPressed() {
        val webView = findViewById<WebView>(R.id.webView)
        webView.visibility = View.GONE
        viewModel.navigateBack()
    }
}

private class TabListener(private val tabChosen: (Int) -> Unit) :
    TabLayout.OnTabSelectedListener {
    override fun onTabSelected(tab: TabLayout.Tab?) = tabChosen.invoke(tab?.position ?: 0)
    override fun onTabUnselected(tab: TabLayout.Tab?) = Unit
    override fun onTabReselected(tab: TabLayout.Tab?) = Unit
}