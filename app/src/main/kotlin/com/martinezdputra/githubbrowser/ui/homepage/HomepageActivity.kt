package com.martinezdputra.githubbrowser.ui.homepage

import android.content.Intent
import android.os.Handler
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.martinezdputra.githubbrowser.R
import com.martinezdputra.githubbrowser.adapter.SearchResultAdapter
import com.martinezdputra.githubbrowser.core.CoreActivity
import com.martinezdputra.githubbrowser.databinding.HomepageActivityBinding
import com.martinezdputra.githubbrowser.datamodel.UserDetailDataModel
import com.martinezdputra.githubbrowser.di.component.DaggerAppComponent
import com.martinezdputra.githubbrowser.ui.detail.DetailActivity
import javax.inject.Inject

class HomepageActivity: CoreActivity<HomepageViewModel>(), SearchResultAdapter.OnUserSelectedListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var mBinding: HomepageActivityBinding

    private val mHandler = Handler()
    private val afterTextChangedTask = Runnable {
        viewModel.fetchUsers()
    }

    override fun createViewModel() = ViewModelProvider(this, viewModelFactory).get(HomepageViewModel::class.java)

    override fun injectComponent() {
        DaggerAppComponent
            .builder()
            .application(application)
            .build()
            .inject(this)
    }

    override fun onInitView(): ViewDataBinding {
        mBinding = DataBindingUtil.setContentView(this, R.layout.homepage_activity)
        mBinding.viewModel = viewModel

        initViewComponents()
        return mBinding
    }

    override fun onUserSelected(selectedUserDataModel: UserDetailDataModel) {
        viewModel.updateSelectedUser(selectedUserDataModel)
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUserDetails()
    }

    private fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun initViewComponents() {
        mBinding.editTextInput.doAfterTextChanged {
            mHandler.removeCallbacks(afterTextChangedTask)
            mHandler.postDelayed(afterTextChangedTask, 500)
        }

        viewModel.users.observe(this) {
            mBinding.recyclerViewUsers.apply {
                adapter = SearchResultAdapter(it, this@HomepageActivity)
                layoutManager = LinearLayoutManager(this@HomepageActivity)
            }
        }

        viewModel.errorMessage.observe(this) {
            it?.also { showToastMessage(it) }
        }

        viewModel.selectedUserId.observe(this) {
            navigateToDetailActivity(it)
        }
    }

    private fun navigateToDetailActivity(userId: String) {
        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_KEY, userId)
        }
        startActivity(intent)
    }
}