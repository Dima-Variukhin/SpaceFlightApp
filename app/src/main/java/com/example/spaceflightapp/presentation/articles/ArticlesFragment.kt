package com.example.spaceflightapp.presentation.articles

import android.os.Bundle
import android.view.View
import com.example.spaceflightapp.core.Retry

class ArticlesFragment : BaseFragment<ArticlesViewModel>() {
    override fun viewModelClass() = ArticlesViewModel::class.java
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ArticleAdapter(object : Retry {
            override fun tryAgain() = viewModel.fetchArticles()
        }
        )
        setAdapter(adapter)
        viewModel.observe(this) {
            it.map(adapter)
        }
        viewModel.init()
    }
}