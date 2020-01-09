package com.viperinius.calappo.ui.newAppointment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.viperinius.calappo.R
import kotlinx.android.synthetic.main.fragment_appointment.*

class NewAppointmentFragment : BottomSheetDialogFragment() {

    private lateinit var newAppointmentViewModel: NewAppointmentViewModel
    private var fragmentView: View? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.fragment_appointment, container, false)
        return fragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        text_Bottom1.setOnClickListener {
            Toast.makeText(context, "TEXT", Toast.LENGTH_SHORT).show()
        }
    }
}
