package com.example.parcial1.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parcial1.R
import com.example.parcial1.model.Subject
import com.example.parcial1.view.adapter.ActivityAdapter
import kotlinx.android.synthetic.main.activity_item.*
import kotlinx.android.synthetic.main.fragment_register_activity.*
import kotlinx.android.synthetic.main.fragment_register_qualifications.*

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterQualificationsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 *
 *
 *
 *  override fun onCreateView(
inflater: LayoutInflater, container: ViewGroup?,
savedInstanceState: Bundle?
): View? {
// Inflate the layout for this fragment
return inflater.inflate(R.layout.fragment_register_qualifications, container, false)
}

 *
 */
class RegisterQualificationsFragment : Fragment() {

    private lateinit var subject: Subject
    private var currentCort: Int = 0

    private lateinit var activityAdapter: ActivityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_qualifications, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activityAdapter = ActivityAdapter()

        subject = arguments?.getSerializable("subject") as Subject
        registrar_actividades.apply {
            layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
            adapter = activityAdapter
        }

        nameSubject_calication.text = "Subject name: ${subject.name}"

        radioCortes.setOnCheckedChangeListener { group, checkedId ->

            currentCort = when (checkedId) {
                R.id.corte1 -> {
                    1
                }
                R.id.corte2 -> {
                    2
                }
                R.id.corte3 -> {
                    3
                }
                else -> 0
            }

            if (currentCort != 0) {
                val qualification = subject.qualifications[currentCort - 1]
                activityAdapter.updateData(qualification.activities)
                porcentaje_completo.text = "Porcentaje completo: ${qualification.totalActivitiesPercent * 100}%"
            }
        }

        add_actividad_button.setOnClickListener {
            if (currentCort != 0) {
                val bundle = bundleOf("qualification" to subject.qualifications[currentCort - 1])
                findNavController().navigate(R.id.registerActivityFragment, bundle)
            }
        }
    }



}