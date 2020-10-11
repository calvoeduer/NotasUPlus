package com.example.parcial1.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.parcial1.R
import com.example.parcial1.model.Subject
import com.example.parcial1.viewmodel.SubjectViewModel
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.fragment_register_subject.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterSubjectFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterSubjectFragment : Fragment() {
    private lateinit var code: TextInputLayout
    private lateinit var name: TextInputLayout
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var subjectViewModel: SubjectViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_subject, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment RegisterSubjectFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterSubjectFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    //code

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subjectViewModel = SubjectViewModel(view.context)

        code = view.findViewById(R.id.codeSubject)
        name = view.findViewById(R.id.nameSubject)

        registrar_button.setOnClickListener {
            saveSubject()
        }
    }

    private fun saveSubject() {

        val codeText = code.editText?.text.toString()
        val nameText = name.editText?.text.toString()

        val subject = Subject(codeText, nameText)

        if (subjectViewModel.saveSubject(subject)) {
            code.editText?.text?.clear()
            name.editText?.text?.clear()
        }


    }
}
