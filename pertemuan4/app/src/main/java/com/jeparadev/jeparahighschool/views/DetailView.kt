package com.jeparadev.jeparahighschool.views

import com.jeparadev.jeparahighschool.models.Highschool

interface DetailView {

    fun showLoading()
    fun hideLoading()
    fun onToast(message: String)
    fun onError(error: String)
    fun onResult(data: Highschool)
}