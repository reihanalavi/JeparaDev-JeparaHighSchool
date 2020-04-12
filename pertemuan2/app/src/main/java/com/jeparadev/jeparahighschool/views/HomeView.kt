package com.jeparadev.jeparahighschool.views

import com.jeparadev.jeparahighschool.models.Highschool

interface HomeView {
    fun showLoading()
    fun hideLoading()
    fun onToast(message: String)
    fun onResult(data: List<Highschool>?)
}