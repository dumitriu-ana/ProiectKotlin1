package com.example.licenta2.ui.facturi.adaugare_facturi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.licenta2.R
import com.example.licenta2.databinding.FragmentAdaugaClientBinding
import com.example.licenta2.databinding.FragmentAdaugaFacturaBinding
import com.example.licenta2.ui.clienti.adaugare_client.AdaugaClientViewModel

class AdaugaFactura : Fragment() {
    private  lateinit var adaugaFacturaViewModel: AdaugaFacturaViewModel
    private var _binding: FragmentAdaugaFacturaBinding? = null;
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val adaugaFacturaViewModel = ViewModelProvider(this).get(AdaugaFacturaViewModel::class.java)
        _binding = FragmentAdaugaFacturaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adaugaFacturaViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textAdaugaFactura.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}