package com.emedinaa.basearchitecture

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CourseAdapter(var courses: List<CourseEntity>) :
    RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    inner class CourseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val textViewName: TextView = view.findViewById(R.id.textViewName)
        private val textViewModality: TextView = view.findViewById(R.id.textViewModality)
        private val textViewDate: TextView = view.findViewById(R.id.textViewDate)
        private val textViewDesc: TextView = view.findViewById(R.id.textViewDesc)
        private val imageView: ImageView = view.findViewById(R.id.imageView)

        fun bind(courseEntity: CourseEntity) {
            with(courseEntity) {
                textViewName.text = name
                //textViewModality.text =
                textViewDate.text = "Fecha de inicio: ".plus(startDate)
                textViewDesc.text = desc
            }
        }
    }

    fun update(courses: List<CourseEntity>) {
        this.courses = courses
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        return CourseViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.bind(courses[position])
    }

    override fun getItemCount(): Int = courses.size
}