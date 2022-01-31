package com.martinezdputra.githubbrowser.datamodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.martinezdputra.githubbrowser.BR

data class UserDetailDataModel(
    val id: String,
    val dpUrl: String,
    private var _name: String? = null,
    private var _bio: String? = null,
    private var _location: String? = null,
    private var _email: String? = null,
    private var _followers: String? = null,
): BaseObservable() {

    var name: String?
        @Bindable get() = _name
        set(value) {
            _name = value
            notifyPropertyChanged(BR.name)
        }

    var bio: String?
        @Bindable get() = _bio
        set(value) {
            _bio = value
            notifyPropertyChanged(BR.bio)
        }

    var location: String?
        @Bindable get() = _location
        set(value) {
            _location = value
            notifyPropertyChanged(BR.location)
        }

    var email: String?
        @Bindable get() = _email
        set(value) {
            _email = value
            notifyPropertyChanged(BR.email)
        }

    var followers: String?
        @Bindable get() = _followers
        set(value) {
            _followers = value
            notifyPropertyChanged(BR.followers)
        }
}