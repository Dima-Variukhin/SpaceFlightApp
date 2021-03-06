package com.myapp.spaceflightapp.presentation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.myapp.spaceflightapp.R

abstract class BaseFragment<T : BaseViewModel> : Fragment() {
    protected lateinit var viewModel: T
    private var recyclerView: RecyclerView? = null
    protected abstract fun viewModelClass(): Class<T>
    private lateinit var layoutManager: LinearLayoutManager
    fun name(): String = javaClass.simpleName
    protected open fun layoutResId() = R.layout.fragment_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as com.myapp.spaceflightapp.MainActivity).supportActionBar?.title = name()

        viewModel = (requireActivity() as com.myapp.spaceflightapp.MainActivity).getViewModel(viewModelClass(), this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutResId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        layoutManager = LinearLayoutManager(requireContext())
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    protected fun setAdapter(adapter: RecyclerView.Adapter<*>) {
        recyclerView?.adapter = adapter
    }
}