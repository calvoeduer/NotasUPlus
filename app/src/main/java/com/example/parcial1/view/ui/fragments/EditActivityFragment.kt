package com.example.parcial1.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment.STYLE_NORMAL
import androidx.navigation.fragment.findNavController
import com.example.parcial1.R
import com.example.parcial1.model.Activity
import com.example.parcial1.viewmodel.SubjectViewModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_edit_activity.*


class EditActivityFragment : Fragment() {

    private lateinit var activity: Activity

    private lateinit var nameField: TextInputLayout
    private lateinit var noteField: TextInputLayout
    private lateinit var percentField: TextInputLayout

    private lateinit var subjectViewModel: SubjectViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater , container: ViewGroup? ,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_activity , container , false)
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        activity = arguments?.getSerializable("activity") as Activity

        subjectViewModel = SubjectViewModel(view.context)

        nameField = view.findViewById(R.id.activity_name_update)
        noteField = view.findViewById(R.id.activity_note_uptade)
        percentField = view.findViewById(R.id.activity_percent_update)

        nameField.editText?.setText(activity.name)
        noteField.editText?.setText(activity.note.toString())
        percentField.editText?.setText(activity.percent.toString())

        update_actividad_button.setOnClickListener {
            updateActivity()
        }
    }

    private fun updateActivity() {
        val nameText = nameField.editText?.text.toString()
        val noteText = noteField.editText?.text.toString().toFloat()
        val percentText = percentField.editText?.text.toString().toFloat()

        if (nameText.isNullOrEmpty() || noteText > 5.0 || percentText > 1.0)
            return

        activity.name = nameText
        activity.note = noteText
        activity.percent = percentText

        if (subjectViewModel.updateActivity(activity)) {

            findNavController().navigate(R.id.listSubjectsFragment)
        }
    }
}