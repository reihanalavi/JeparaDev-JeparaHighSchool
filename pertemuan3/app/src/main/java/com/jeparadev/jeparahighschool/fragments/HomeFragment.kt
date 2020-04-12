package com.jeparadev.jeparahighschool.fragments

import android.app.Application
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.jeparadev.jeparahighschool.R
import com.jeparadev.jeparahighschool.adapters.HighschoolAdapter
import com.jeparadev.jeparahighschool.clientservice.ApiRepository
import com.jeparadev.jeparahighschool.clientservice.RetrofitBuilder
import com.jeparadev.jeparahighschool.models.Highschool
import com.jeparadev.jeparahighschool.presenters.HomePresenter
import com.jeparadev.jeparahighschool.views.HomeView
import kotlinx.android.synthetic.main.fragment_home.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.support.v4.alert
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.selector
import org.jetbrains.anko.support.v4.toast
import retrofit2.Retrofit

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(), HomeView {

    private var schoolLevel = listOf(
        "sma",
        "smk"
    )

    private var highschool: MutableList<Highschool> = mutableListOf()

    lateinit var retrofit: Retrofit
    lateinit var apiRepository: ApiRepository
    lateinit var application: Application

    lateinit var presenter: HomePresenter
    lateinit var adapter: HighschoolAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.title = "Jepara High School"

        retrofit = RetrofitBuilder.getRetrofit()
        apiRepository = retrofit.create(ApiRepository::class.java)
        application = Application()

        presenter = HomePresenter(this, apiRepository, application, view.context)

        //init recycler view and adapter
        view.recyclerView_home.layoutManager = LinearLayoutManager(view.context)
        adapter = HighschoolAdapter(view.context, highschool) {
            //to details
        }
        adapter.notifyDataSetChanged()
        view.recyclerView_home.adapter = adapter

        view.swipeRefresh_home.onRefresh {
            val schoolSelected = view.button_home.text.toString()

            if(schoolSelected == "Choose School Level".toUpperCase()) {
                toast("Please choose school level first")
            } else {
                presenter.getSchoolFromServer("032000", schoolSelected)
            }
        }

        view.button_home.onClick {
            selector("Choose School Level", schoolLevel) { dialogInterface: DialogInterface, i: Int ->
                val schoolSelected = schoolLevel[i]

                view.button_home.text = schoolSelected
                presenter.getSchoolFromServer("032000", schoolSelected)
            }
        }

    }

    override fun showLoading() {
        view?.recyclerView_home?.visibility = View.INVISIBLE
        view?.progressBar_home?.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        view?.recyclerView_home?.visibility = View.VISIBLE
        view?.progressBar_home?.visibility = View.INVISIBLE
        view?.swipeRefresh_home?.isRefreshing = false
    }

    override fun onToast(message: String) {
        toast(message)
    }

    override fun onResult(data: List<Highschool>?) {
        highschool.clear()

        if(data != null) {
            if(data[0].bentuk != view?.button_home?.text.toString().toUpperCase()) {
                alert("High Schools you've searched aren't in the database", "Empty") {
                    positiveButton("OK") {}
                }.show()
            } else {
                highschool.addAll(data)
            }
        } else {
            onToast("High school data is empty.")
        }
        Log.d("HIGH SCHOOL SIZE", highschool.size.toString())
        adapter.notifyDataSetChanged()
    }

}
