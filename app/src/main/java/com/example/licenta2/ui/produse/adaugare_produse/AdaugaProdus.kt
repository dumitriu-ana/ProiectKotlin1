package com.example.licenta2.ui.produse.adaugare_produse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.licenta2.R
import com.example.licenta2.databinding.FragmentAdaugaClientBinding
import com.example.licenta2.databinding.FragmentAdaugaProdusBinding
import com.example.licenta2.ui.clienti.adaugare_client.AdaugaClientViewModel

class AdaugaProdus : Fragment() {
    private  lateinit var adaugaProdusViewModel: AdaugaProdusViewModel
    private var _binding: FragmentAdaugaProdusBinding? = null;
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val adaugaProdusViewModel = ViewModelProvider(this).get(AdaugaProdusViewModel::class.java)
        _binding = FragmentAdaugaProdusBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adaugaProdusViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textAdaugaProdus.text = it

        })
        return root
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}