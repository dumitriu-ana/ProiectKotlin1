package com.example.licenta2.ui.autentificare

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.licenta2.R
import com.example.licenta2.databinding.FragmentInregistrareBinding


class InregistrareFragment : Fragment() {
    private lateinit var inregistrareViewModel: InregistrareViewModel
    private var _binding: FragmentInregistrareBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        inregistrareViewModel = ViewModelProvider(this).get(InregistrareViewModel::class.java)
        _binding = FragmentInregistrareBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}