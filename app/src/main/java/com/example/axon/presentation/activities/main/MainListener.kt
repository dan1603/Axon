package com.example.axon.presentation.activities.main

interface MainListener {
    fun onRepositoryClicked(url: String)
    fun openUserDetail(userId: String)
}