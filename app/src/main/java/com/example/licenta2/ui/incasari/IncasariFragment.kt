package com.example.licenta2.ui.incasari

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.licenta2.R
import com.example.licenta2.databinding.FragmentFacturiBinding
import com.example.licenta2.databinding.FragmentIncasariBinding
import com.example.licenta2.ui.facturi.FacturiViewModel


class IncasariFragment : Fragment() {
    private lateinit var incasariViewModel: IncasariViewModel
    private var _binding: FragmentIncasariBinding? = null;
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val incasariViewModel = ViewModelProvider(this).get(IncasariViewModel::class.java)
        _binding = FragmentIncasariBinding.inflate(inflater, container, false)
        val root: View = binding.root


       binding.butonAdaugaIncasare.setOnClickListener {
        findNavController().navigate(R.id.action_incasariFragment_to_adaugaIncasare)
        }
        incasariViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textIncasari.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}