package com.example.spclone.data

object UserSession {
    var isLoggedIn: Boolean = false
        private set

    fun login() {
        isLoggedIn = true
    }

    fun logout() {
        isLoggedIn = false
    }
}