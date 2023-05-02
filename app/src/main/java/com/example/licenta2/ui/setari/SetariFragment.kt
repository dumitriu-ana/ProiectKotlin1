package com.example.licenta2.ui.setari

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.licenta2.R
import com.example.licenta2.databinding.FragmentFacturiBinding
import com.example.licenta2.databinding.FragmentSetariBinding
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.ui.facturi.FacturiViewModel


class SetariFragment : Fragment() {

    private lateinit var setariViewModel: SetariViewModel
    private var _binding: FragmentSetariBinding? = null;
    private val binding get() = _binding!!

    private lateinit var appDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setariViewModel = ViewModelProvider(this).get(SetariViewModel::class.java)
       _binding =  FragmentSetariBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setariViewModel.initDatabase(requireContext())


      binding.btnSetariStergereIncasari.setOnClickListener {
            setariViewModel.stergereIncasari()
            Toast.makeText(requireContext(), "incasari sterse", Toast.LENGTH_LONG).show()
        }

        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}