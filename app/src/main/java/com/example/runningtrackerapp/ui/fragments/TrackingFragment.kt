package com.example.runningtrackerapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.runningtrackerapp.R
import com.example.runningtrackerapp.databinding.FragmentRunBinding
import com.example.runningtrackerapp.databinding.FragmentSetupBinding
import com.example.runningtrackerapp.databinding.FragmentTrackingBinding
import com.example.runningtrackerapp.other.Constants.ACTION_START_ON_RESUME_SERVICE
import com.example.runningtrackerapp.services.TrackingService
import com.example.runningtrackerapp.ui.viewmodels.MainViewModel
import com.google.android.gms.maps.GoogleMap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TrackingFragment : Fragment(R.layout.fragment_tracking) {

    private lateinit var bindingTracking: FragmentTrackingBinding
    private val viewModel: MainViewModel by viewModels()
    private var map: GoogleMap? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingTracking = FragmentTrackingBinding.inflate(layoutInflater)
        return bindingTracking.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindingTracking.btnToggleRun.setOnClickListener {
            sendCommandToService(ACTION_START_ON_RESUME_SERVICE)
        }
        bindingTracking.mapView.onCreate(savedInstanceState)

        bindingTracking.mapView.getMapAsync {
            map = it
        }
    }

    private fun sendCommandToService(action: String) {
        Intent(requireContext(), TrackingService::class.java).also {
            it.action = action
            requireContext().startService(it)
        }

    }

    override fun onResume() {
        super.onResume()
        bindingTracking.mapView?.onResume()
    }

    override fun onStart() {
        super.onStart()
        bindingTracking.mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        bindingTracking.mapView?.onStop()
    }

    override fun onPause() {
        super.onPause()
        bindingTracking.mapView?.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        bindingTracking.mapView?.onLowMemory()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        bindingTracking.mapView?.onSaveInstanceState(outState)

    }
}