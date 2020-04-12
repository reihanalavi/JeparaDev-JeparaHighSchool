package com.jeparadev.jeparahighschool.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.jeparadev.jeparahighschool.R
import com.jeparadev.jeparahighschool.databinding.ItemsSchoolBinding
import com.jeparadev.jeparahighschool.models.Highschool
import kotlinx.android.synthetic.main.items_school.view.*
import org.jetbrains.anko.sdk25.coroutines.onClick

class HighschoolAdapter(
    private val context: Context,
    private var highschool: List<Highschool>,
    private val listener: (Highschool) -> Unit
) : RecyclerView.Adapter<HighschoolAdapter.SchoolViewHolder>() {

    class SchoolViewHolder(private val itemViews: ItemsSchoolBinding): RecyclerView.ViewHolder(itemViews.root) {

        fun bindData(data: Highschool, listener: (Highschool) -> Unit) {

            itemViews.highschool = data

            itemViews.root.onClick {
                listener(data)
            }

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SchoolViewHolder {

        val inflater = LayoutInflater.from(context)
        val views = DataBindingUtil.inflate<ItemsSchoolBinding>(inflater, R.layout.items_school, parent, false)
        return SchoolViewHolder(views)

    }

    override fun getItemCount(): Int = highschool.size

    override fun onBindViewHolder(holder: SchoolViewHolder, position: Int) {
        holder.bindData(highschool[position], listener)
    }
}