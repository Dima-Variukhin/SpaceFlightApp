package com.myapp.spaceflightapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.appbar.MaterialToolbar
import com.myapp.spaceflightapp.core.Retry
import com.myapp.spaceflightapp.presentation.launches.fragments.SearchResultsFragment
import com.myapp.spaceflightapp.presentation.launches.viewmodels.MainScreenViewModel
import com.myapp.spaceflightapp.presentation.upcoming.viewmodels.MainUpcomingScreenViewModel

class UpcomingActivity : AppCompatActivity(), Retry {
    private lateinit var viewModel: MainUpcomingScreenViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upcoming)
        val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val navController = findNavController(R.id.nav_host_fragment_upcoming)
        NavigationUI.setupActionBarWithNavController(this, navController)

        viewModel = ViewModelProviders.of(this).get(MainUpcomingScreenViewModel::class.java)
        viewModel.searchState.observe(this) { data -> navController.navigate(data) }
        viewModel.progressState.observe(this) { show ->
            progressBar.visibility = if (show) View.VISIBLE else View.GONE
        }

        viewModel.errorState.observe(this) { message ->
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        } else false
        return super.onOptionsItemSelected(item)
    }

    override fun tryAgain() = viewModel.fetchUp()
}