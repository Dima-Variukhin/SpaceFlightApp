package com.myapp.spaceflightapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.myapp.spaceflightapp.core.Retry
import com.myapp.spaceflightapp.presentation.launches.fragments.SearchResultsFragment
import com.myapp.spaceflightapp.presentation.launches.viewmodels.MainScreenViewModel
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.floatingactionbutton.FloatingActionButton

class SearchActivity : AppCompatActivity(), Retry {
    private lateinit var viewModel: MainScreenViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController)

        viewModel = ViewModelProviders.of(this).get(MainScreenViewModel::class.java)
        viewModel.searchState.observe(this) { data ->
            navController.navigate(data.first,
                Bundle().apply { putString(SearchResultsFragment.EXTRA_YEAR, data.second) })
        }
        viewModel.progressState.observe(this) { show ->
            progressBar.visibility = if (show) View.VISIBLE else View.GONE
        }

        viewModel.errorState.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
        val goToUpcoming = findViewById<ImageButton>(R.id.search_upcoming)
        goToUpcoming.setOnClickListener {
            intent = Intent(this, UpcomingActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.inputType = InputType.TYPE_CLASS_NUMBER
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String) = fetch(query)
            override fun onQueryTextChange(newText: String) = fetch(newText)

            private fun fetch(text: String): Boolean {
                viewModel.fetch(text)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val item1 = findViewById<ImageView>(R.id.item1)
        val item2 = findViewById<ImageView>(R.id.item2)
        val item3 = findViewById<ImageView>(R.id.item3)
        val item4 = findViewById<ImageView>(R.id.item4)
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        } else item.let {
            item1.visibility = View.VISIBLE
            item2.visibility = View.VISIBLE
            item3.visibility = View.INVISIBLE
            item4.visibility = View.INVISIBLE
            return super.onOptionsItemSelected(it)
        }
    }

    override fun tryAgain() = viewModel.fetch()
}
