package com.martinezdputra.githubbrowser.repository

import com.martinezdputra.githubbrowser.datamodel.response.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(urlSearchUsers)
    fun getSearchUsers(@Query("q") query: String): Observable<SearchUserResponse>

    @GET(urlUser)
    fun getUserDetails(@Path("user_id") userId: String): Observable<UserDetailResponse>

    @GET(urlUserRepositories)
    fun getUserRepositories(@Path("user_id") userId: String): Observable<List<UserRepositoriesResponse>>

    companion object {
        private const val urlSearchUsers = "search/users"
        private const val urlUser = "users/{user_id}"
        private const val urlUserRepositories = "users/{user_id}/repos"
    }
}