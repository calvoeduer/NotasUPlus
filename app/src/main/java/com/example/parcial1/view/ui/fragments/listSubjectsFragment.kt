package com.example.parcial1.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parcial1.R
import com.example.parcial1.model.Activity
import com.example.parcial1.model.Subject
import com.example.parcial1.view.adapter.SubjectAdapter
import com.example.parcial1.view.adapter.SubjectListener
import com.example.parcial1.viewmodel.SubjectViewModel
import kotlinx.android.synthetic.main.fragment_list_subjects.*
import kotlinx.android.synthetic.main.fragment_register_qualifications.*
import kotlin.reflect.KVariance


/**
 * A simple [Fragment] subclass.
 * Use the [listSubjectsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class listSubjectsFragment : Fragment(), SubjectListener {
    private lateinit var subjectAdapter: SubjectAdapter
    private  lateinit var subjectViewModel: SubjectViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_subjects, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subjectViewModel = SubjectViewModel(view.context)
        subjectViewModel.refresh()

        subjectAdapter = SubjectAdapter(this)

        subjects.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = subjectAdapter
        }

        subjectViewModel.subjects.observe(viewLifecycleOwner, Observer {
            subjects -> subjectAdapter.updateData(subjects)
        })

        subjectViewModel.isLoading.observe(viewLifecycleOwner, Observer {
            if(it != null){
                rv_loading.visibility = View.INVISIBLE
            }
        })
    }

    override fun oneSubjectTap(subject: Subject, index: Int) {
        val bundle = bundleOf("subject" to subject)
        findNavController().navigate(R.id.registerQualificationsFragment, bundle)
    }

    override fun onSubjectDeleteButtonTap(subject: Subject, position: Int) {
      if (subjectViewModel.deleteSubject(subject.code))
          subjectViewModel.refresh()
    }

    override fun onUpdateSubjectButtontap(subject: Subject, index: Int) {
        val bundle = bundleOf("subject" to subject)
        findNavController().navigate(R.id.updateSubjectFragment, bundle)
    }


}