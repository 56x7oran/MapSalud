package ec.edu.mapsalud.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ec.edu.mapsalud.R
import ec.edu.mapsalud.databinding.FragmentCentersBinding
import ec.edu.mapsalud.databinding.FragmentHomeBinding

class CentersFragment : Fragment() {

    lateinit var binding: FragmentCentersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCentersBinding.inflate(layoutInflater, container, false);

        return binding.root
    }
}