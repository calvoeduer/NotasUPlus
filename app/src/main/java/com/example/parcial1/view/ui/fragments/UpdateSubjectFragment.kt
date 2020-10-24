package com.example.parcial1.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.parcial1.R
import com.example.parcial1.model.Subject
import com.example.parcial1.network.ApiCallback
import com.example.parcial1.viewmodel.SubjectViewModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_update_subject.*
import kotlinx.android.synthetic.main.subject_item.*
import kotlinx.android.synthetic.main.subject_item.subject_code as subject_code1


/**
 * A simple [Fragment] subclass.
 * Use the [UpdateSubjectFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UpdateSubjectFragment : Fragment() {
    private lateinit var subjectViewModel: SubjectViewModel
    private lateinit var subject: Subject
    private lateinit var subject_name: TextInputLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_subject, container, false)
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?) {
        super.onViewCreated(view , savedInstanceState)

        subject = arguments?.getSerializable("subject") as Subject
        subjectViewModel = SubjectViewModel(view.context)

        subject_code.text = "Codigo: "+ subject.code
        subject_name = view.findViewById(R.id.nameUpdateSubject)
        nameUpdateSubject.editText?.setText(subject.name)

        update_subject_button.setOnClickListener {
            if (subject_name.editText?.text?.isNotBlank()!!) {
                subject = Subject(subject.code, subject_name.editText?.text.toString())


                subjectViewModel.updateSubject(subject, object : ApiCallback<Subject>{
                    override fun onFail(exception: Throwable) {
                        return
                    }

                    override fun onSuccess(result: Subject?) {
                        findNavController().navigate(R.id.listSubjectsFragment)

                    }
                })


            }
        }
    }

/*                    findNavController().navigate(R.id.listSubjectsFragment)
*/


}