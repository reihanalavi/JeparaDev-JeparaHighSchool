package com.jeparadev.jeparahighschool.fragments

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.jeparadev.jeparahighschool.R
import com.jeparadev.jeparahighschool.databinding.FragmentDetailBinding
import com.jeparadev.jeparahighschool.databinding.FragmentDetailBindingImpl
import com.jeparadev.jeparahighschool.models.Highschool
import com.jeparadev.jeparahighschool.presenters.DetailPresenter
import com.jeparadev.jeparahighschool.views.DetailView

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment(), DetailView {

    lateinit var dataBinding: FragmentDetailBinding

    lateinit var presenter: DetailPresenter
    lateinit var application: Application

    var schoolUid: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            schoolUid = DetailFragmentArgs.fromBundle(it).schoolUid
        }

        application = Application()
        presenter = DetailPresenter(this, view.context, application)

        presenter.getSchoolDetailsByUid(schoolUid)
    }

    override fun showLoading() {}

    override fun hideLoading() {}

    override fun onToast(message: String) {}

    override fun onError(error: String) {}

    override fun onResult(data: Highschool) {
        dataBinding.highschool = data

        activity?.title = dataBinding.highschool?.sekolah
    }

}
