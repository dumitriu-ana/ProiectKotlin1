package com.example.licenta2.ui.incasari.adaugare_incasare

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.licenta2.R
import com.example.licenta2.databinding.FragmentAdaugaClientBinding
import com.example.licenta2.databinding.FragmentAdaugaIncasareBinding
import com.example.licenta2.ui.clienti.adaugare_client.AdaugaClientViewModel

class AdaugaIncasare : Fragment() {
    private  lateinit var adaugaIncasareViewModel: AdaugaIncasareViewModel
    private var _binding: FragmentAdaugaIncasareBinding? = null;
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val adaugaIncasareViewModel = ViewModelProvider(this).get(AdaugaIncasareViewModel::class.java)
        _binding = FragmentAdaugaIncasareBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adaugaIncasareViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textAdaugaIncasare.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}