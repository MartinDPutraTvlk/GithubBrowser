package com.martinezdputra.githubbrowser.ui.homepage

import androidx.lifecycle.MutableLiveData
import com.martinezdputra.githubbrowser.core.CoreViewModel
import com.martinezdputra.githubbrowser.datamodel.UserDetailDataModel
import com.martinezdputra.githubbrowser.repository.accessor.UsersAccessor
import javax.inject.Inject

class HomepageViewModel @Inject constructor(private val usersRepository: UsersAccessor): CoreViewModel() {
    var inputAmount: String? = null
    val users = MutableLiveData<List<UserDetailDataModel>>()
    val errorMessage = MutableLiveData<String>()
    val selectedUserId = MutableLiveData<String>()

    fun fetchUsers() {
        val input = inputAmount
        if(!input.isNullOrEmpty()) {
            usersRepository.fetchUsersData(input)
                .attachStandardSchedulers()
                .subscribe({ response ->
                    users.value = response.items.map { item ->
                        UserDetailDataModel(
                            id = item.userId,
                            dpUrl = item.avatarUrl
                        )
                    }
                }, {t -> t.printStackTrace()})
                .addToComposite()
        } else {
            users.value = emptyList()
        }
    }

    fun updateSelectedUser(userDataModel: UserDetailDataModel) {
        selectedUserId.value = userDataModel.id
    }
}