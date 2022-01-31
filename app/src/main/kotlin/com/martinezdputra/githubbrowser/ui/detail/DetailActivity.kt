package com.martinezdputra.githubbrowser.ui.detail

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.martinezdputra.githubbrowser.R
import com.martinezdputra.githubbrowser.adapter.SearchResultAdapter
import com.martinezdputra.githubbrowser.adapter.UserRepositoriesAdapter
import com.martinezdputra.githubbrowser.core.CoreActivity
import com.martinezdputra.githubbrowser.databinding.DetailActivityBinding
import com.martinezdputra.githubbrowser.di.component.DaggerAppComponent
import javax.inject.Inject

class DetailActivity: CoreActivity<DetailViewModel>() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mBinding: DetailActivityBinding

    override fun createViewModel() = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

    override fun onInitView(): ViewDataBinding {
        mBinding = DataBindingUtil.setContentView(this, R.layout.detail_activity)
        mBinding.viewModel = viewModel

        val userId = intent.getStringExtra(EXTRA_KEY)!!
        viewModel.prepareData(userId)

        initViewComponents()

        return mBinding
    }

    override fun injectComponent() {
        DaggerAppComponent
            .builder()
            .application(application)
            .build()
            .inject(this)
    }

    private fun initViewComponents() {
        viewModel.userDetail.observe(this) {
            mBinding.userData = it
        }

        viewModel.repositories.observe(this) {
            if(!it.isNullOrEmpty()) {
                mBinding.textViewNoRepositories.visibility = View.GONE
                mBinding.recyclerViewRepositories.apply {
                    adapter = UserRepositoriesAdapter(it)
                    layoutManager = LinearLayoutManager(this@DetailActivity)
                }
            } else {
                mBinding.textViewNoRepositories.visibility = View.VISIBLE
            }
        }
    }

    companion object {
        const val EXTRA_KEY = "detail.activity.extra.key"
    }
}