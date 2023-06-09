package com.example.licenta2.ui.acasa

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.licenta2.R
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.licenta2.FirebaseInregistrare
import com.example.licenta2.databinding.FragmentAcasaBinding



class AcasaFragment : Fragment() {
    private lateinit var acasaViewModel: AcasaViewModel
    private var _binding: FragmentAcasaBinding? = null;
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

         acasaViewModel = ViewModelProvider(this).get(AcasaViewModel::class.java)
        _binding = FragmentAcasaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textAcasa
        acasaViewModel.text.observe(viewLifecycleOwner, Observer {
           // binding.textAcasa.text = it
        })
        binding.butonAcasa.setOnClickListener {
           val intent = Intent(requireContext(), FirebaseInregistrare::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}