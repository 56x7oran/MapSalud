package ec.edu.mapsalud.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ec.edu.mapsalud.R
import ec.edu.mapsalud.databinding.FragmentBookingBinding
import ec.edu.mapsalud.databinding.FragmentCentersBinding

class BookingFragment : Fragment() {

    lateinit var binding: FragmentBookingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookingBinding.inflate(layoutInflater, container, false);

        return binding.root
    }
}