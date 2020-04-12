package com.jeparadev.jeparahighschool.presenters

import android.app.Application
import android.content.Context
import com.jeparadev.jeparahighschool.clientservice.ApiRepository
import com.jeparadev.jeparahighschool.clientservice.BaseViewModel
import com.jeparadev.jeparahighschool.models.Highschool
import com.jeparadev.jeparahighschool.models.HighschoolDatabase
import com.jeparadev.jeparahighschool.views.HomeView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch

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

                        storeHighschoolsLocally(it.data)
                    },
                    {
                        view.hideLoading()
                        view.onToast("Error occured. Loading highschools from database...")

                        getSchoolFromDatabase()
                        it.printStackTrace()
                    }
                )
        )
    }

    private fun getSchoolFromDatabase() {
        view.showLoading()

        launch {
            val highschools = HighschoolDatabase(context).highschoolDao().getHighschools()
            retrieveSchools(highschools)

            view.hideLoading()
            view.onToast("Schools retrieved from the database")
        }
    }

    private fun storeHighschoolsLocally(responses: List<Highschool>?) {
        launch {
            val dao = HighschoolDatabase(context).highschoolDao()
            dao.deleteHighschools()
            responses?.toTypedArray()?.let {
                dao.insertHighschools(*it)
            }

            var i = 0
            while (i < responses?.size!!) {
                val idGetAfterInsert = dao.getHighschoolById(responses[i].id.toString())
                responses[i].uid = idGetAfterInsert.uid
                ++i
            }
            retrieveSchools(responses)
        }
    }

    private fun retrieveSchools(responses: List<Highschool>?) {
        view.onResult(responses)
    }

}