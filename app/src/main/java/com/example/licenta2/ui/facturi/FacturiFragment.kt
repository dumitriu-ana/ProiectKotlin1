package com.example.licenta2.ui.facturi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.licenta2.R
import com.example.licenta2.databinding.FragmentAcasaBinding
import com.example.licenta2.databinding.FragmentFacturiBinding
import com.example.licenta2.ui.acasa.AcasaViewModel


class FacturiFragment : Fragment() {
    private lateinit var facturiViewModel: FacturiViewModel
    private var _binding: FragmentFacturiBinding? = null;
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val facturiViewModel = ViewModelProvider(this).get(FacturiViewModel::class.java)
        _binding = FragmentFacturiBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textAcasa
        facturiViewModel.text.observe(viewLifecycleOwner, Observer {
            //binding.textFacturi.text = it
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}