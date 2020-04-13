package com.jeparadev.jeparahighschool.presenters

import android.app.Application
import android.content.Context
import com.jeparadev.jeparahighschool.clientservice.BaseViewModel
import com.jeparadev.jeparahighschool.models.Highschool
import com.jeparadev.jeparahighschool.models.HighschoolDatabase
import com.jeparadev.jeparahighschool.views.DetailView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DetailPresenter(val view: DetailView, val context: Context, application: Application): BaseViewModel(application) {

    fun getSchoolDetailsByUid(uid: Int) {
        view.showLoading()

        launch {
            val highschool = HighschoolDatabase(context).highschoolDao().getHighschoolByUid(uid)
            retrieveSchoolDetails(highschool)

            view.hideLoading()

        }
    }

    private fun retrieveSchoolDetails(response: Highschool) {
        view.onResult(response)
    }

}