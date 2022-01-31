package com.martinezdputra.githubbrowser.datamodel

data class UserRepositoryDataModel(
    val name: String,
    val description: String?,
    val stars: Int,
    val lastUpdated: String,
    val ownerDpUrl: String
)
