package com.jeparadev.jeparahighschool.presenters

import android.app.Application
import android.content.Context
import com.jeparadev.jeparahighschool.clientservice.ApiRepository
import com.jeparadev.jeparahighschool.clientservice.BaseViewModel
import com.jeparadev.jeparahighschool.models.Highschool
import com.jeparadev.jeparahighschool.views.HomeView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class HomePresenter(val view: HomeView, val apiRepository: ApiRepository, application: Application, var context: Context): BaseViewModel(application) {

    lateinit var compositeDisposable: CompositeDisposable

    fun getSchoolFromServer(regionCode: String, schoolKind: String) {
        view.showLoading()

        compositeDisposable = CompositeDisposable()
        compositeDisposable.add(
            apiRepository
                .getHighschools(regionCode, schoolKind)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        view.hideLoading()
                        view.onToast("Highschools retrieved from the server")

                        retrieveSchools(it.data)
                    },
                    {
                        view.hideLoading()
                        view.onToast("Error occured. Try again.")

                        it.printStackTrace()
                    }
                )
        )
    }

    fun retrieveSchools(responses: List<Highschool>?) {
        view.onResult(responses)
    }

}