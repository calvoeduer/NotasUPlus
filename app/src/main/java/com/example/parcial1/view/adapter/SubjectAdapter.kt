package com.example.parcial1.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parcial1.R
import com.example.parcial1.model.Subject

class SubjectAdapter(private val subjectListener: SubjectListener) : RecyclerView.Adapter<SubjectAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val code :TextView = itemView.findViewById(R.id.subject_code)
        val name : TextView = itemView.findViewById(R.id.subject_name)
        val qualifications: TextView = itemView.findViewById(R.id.subject_qualifications)
        val definitive: TextView = itemView.findViewById(R.id.subject_definitive)
        val buttonDeleteSubject : Button = itemView.findViewById(R.id.delete_subject)
        val buttonUpdateSubject: Button = itemView.findViewById(R.id.edit_subject_item)


    }

    var subjects = ArrayList<Subject>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.subject_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val subject = subjects[position]
        holder.code.text = "Code: " + subject.code
        holder.name.text = subject.name
        holder.qualifications.text = "Calificaciones: ${subject.qualifications[0].total}, ${subject.qualifications[1].total}, ${subject.qualifications[2].total}"
        holder.definitive.text = "Definitiva: ${subject.definitive}"
        holder.buttonDeleteSubject.setOnClickListener { subjectListener.onSubjectDeleteButtonTap(subject, position) }
        holder.buttonUpdateSubject.setOnClickListener { subjectListener.onUpdateSubjectButtontap(subject, position) }

        holder.itemView.setOnClickListener {
            subjectListener.oneSubjectTap(subject, position)
        }
    }

    override fun getItemCount(): Int {
        return subjects.size
    }

    fun updateData(newSubject: List<Subject>){
        subjects.clear()
        subjects.addAll(newSubject)
        notifyDataSetChanged()
    }

}