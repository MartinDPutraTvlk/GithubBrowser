package com.martinezdputra.githubbrowser.repository.datastore

import com.martinezdputra.githubbrowser.datamodel.response.*
import io.reactivex.Observable
import javax.inject.Inject

class UserLocalDataStore @Inject constructor(): UserDataStore {
    override fun getSearchUsers(query: String): Observable<SearchUserResponse> {
        TODO("Not yet implemented")
    }

    override fun getUserDetails(userId: String): Observable<UserDetailResponse> {
        TODO("Not yet implemented")
    }

    override fun getUserRepositories(userId: String): Observable<List<UserRepositoriesResponse>> {
        TODO("Not yet implemented")
    }
}