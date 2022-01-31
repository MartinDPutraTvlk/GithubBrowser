package com.martinezdputra.githubbrowser.repository.datastore

import com.martinezdputra.githubbrowser.datamodel.response.*
import io.reactivex.Observable

interface UserDataStore {
    fun getSearchUsers(query: String): Observable<SearchUserResponse>

    fun getUserDetails(userId: String): Observable<UserDetailResponse>

    fun getUserRepositories(userId: String): Observable<List<UserRepositoriesResponse>>
}