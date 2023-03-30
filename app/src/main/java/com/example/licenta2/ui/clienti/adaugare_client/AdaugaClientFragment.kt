package com.example.licenta2.ui.clienti.adaugare_client

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.licenta2.databinding.FragmentAdaugaClientBinding


class AdaugaClientFragment : Fragment() {
    private  lateinit var adaugaClientViewModel: AdaugaClientViewModel
    private var _binding: FragmentAdaugaClientBinding? = null;
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val adaugaClientViewModel = ViewModelProvider(this).get(AdaugaClientViewModel::class.java)
        _binding = FragmentAdaugaClientBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adaugaClientViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textAdaugaClient.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}