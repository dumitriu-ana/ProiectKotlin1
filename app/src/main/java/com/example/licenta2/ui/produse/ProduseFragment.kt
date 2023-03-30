package com.example.licenta2.ui.produse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.licenta2.R
import com.example.licenta2.databinding.FragmentClientiBinding
import com.example.licenta2.databinding.FragmentProduseBinding


class ProduseFragment : Fragment() {
    private lateinit var produseViewModel: ProduseViewModel
    private var _binding: FragmentProduseBinding? = null;
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val produseViewModel = ViewModelProvider(this).get(ProduseViewModel::class.java)
        _binding = FragmentProduseBinding.inflate(inflater, container, false)
        val root: View = binding.root

       binding.butonAdaugaProdus.setOnClickListener {
            findNavController().navigate(R.id.action_produseFragment_to_adaugaProdus)
        }
        produseViewModel.text.observe(viewLifecycleOwner, Observer {
           binding.textProduse.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}