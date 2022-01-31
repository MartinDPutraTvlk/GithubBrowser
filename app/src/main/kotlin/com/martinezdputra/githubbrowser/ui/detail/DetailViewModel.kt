package com.martinezdputra.githubbrowser.ui.detail

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.martinezdputra.githubbrowser.R
import com.martinezdputra.githubbrowser.core.CoreViewModel
import com.martinezdputra.githubbrowser.datamodel.UserRepositoryDataModel
import com.martinezdputra.githubbrowser.datamodel.UserDetailDataModel
import com.martinezdputra.githubbrowser.helper.DateHelper
import com.martinezdputra.githubbrowser.repository.accessor.UsersAccessor
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val usersRepository: UsersAccessor,
    private val injectedContext: Context,
    private val dateHelper: DateHelper
    ): CoreViewModel() {

    val userDetail = MutableLiveData<UserDetailDataModel>()
    val repositories = MutableLiveData<List<UserRepositoryDataModel>>()

    fun prepareData(userId: String) {
        fetchUserDetails(userId)
        fetchUserRepositories(userId)
    }

    private fun fetchUserDetails(userId: String) {
        usersRepository.fetchUserDetails(userId)
            .attachStandardSchedulers()
            .subscribe({ response ->
                userDetail.value = UserDetailDataModel(response.userId, response.avatarUrl).apply {
                    name = response.name
                    bio = response.bio
                    location = response.location
                    email = response.email
                    followers = injectedContext.getString(
                        R.string.text_followers_x_following_count,
                        response.followersCount,
                        response.followingCount)
                }
            }, { e -> e.printStackTrace() })
            .addToComposite()
    }

    private fun fetchUserRepositories(userId: String) {
        usersRepository.fetchUserRepositories(userId)
            .attachStandardSchedulers()
            .subscribe({ response ->
                repositories.value = response.map {
                    val lastUpdateFormatted = dateHelper.getFormattedPastTime(it.lastUpdated)
                    UserRepositoryDataModel(
                        it.name,
                        it.description,
                        it.stars,
                        injectedContext.getString(R.string.text_updated_x_ago, lastUpdateFormatted),
                        it.owner.avatarUrl
                    )
                }
            }, { e -> e.printStackTrace() })
            .addToComposite()
    }
}