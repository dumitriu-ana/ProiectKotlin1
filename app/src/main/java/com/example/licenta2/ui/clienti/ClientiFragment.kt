package com.example.licenta2.ui.clienti

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.licenta2.R
import com.example.licenta2.databinding.FragmentAcasaBinding
import com.example.licenta2.databinding.FragmentClientiBinding
import com.example.licenta2.ui.acasa.AcasaViewModel

class ClientiFragment : Fragment() {
    private lateinit var clientiViewModel: ClientiViewModel
    private var _binding: FragmentClientiBinding? = null;
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val clientiViewModel = ViewModelProvider(this).get(ClientiViewModel::class.java)
        _binding = FragmentClientiBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.butonAdaugaClient.setOnClickListener {
            findNavController().navigate(R.id.action_clientiFragment_to_adaugaClientFragment)
        }
        clientiViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textClienti.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}