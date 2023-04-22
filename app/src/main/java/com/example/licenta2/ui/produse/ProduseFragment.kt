package com.example.licenta2.ui.produse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.licenta2.R
import com.example.licenta2.databinding.FragmentProduseBinding
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Client
import com.example.licenta2.persistence.entities.Produs
import com.example.licenta2.ui.clienti.ClientAdaptor


class ProduseFragment : Fragment() {
    private lateinit var produseViewModel: ProduseViewModel
    private var _binding: FragmentProduseBinding? = null;
    private val binding get() = _binding!!

    private lateinit var appDatabase: AppDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var produsAdaptor: ProdusAdaptor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        produseViewModel = ViewModelProvider(this).get(ProduseViewModel::class.java)
        _binding = FragmentProduseBinding.inflate(inflater, container, false)
        val root: View = binding.root

        produseViewModel.initDatabase(requireContext())

        // RV
        recyclerView = binding.rvProduse
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        produsAdaptor = ProdusAdaptor(requireContext(), emptyList())
        recyclerView.adapter = produsAdaptor
        produseViewModel.getAllProduse().observe(viewLifecycleOwner, Observer { produseList ->
            val produseListNormal: List<Produs> = produseList
            produsAdaptor = ProdusAdaptor(requireContext(), produseListNormal)
            recyclerView.adapter = produsAdaptor
        })

       binding.butonAdaugaProdus.setOnClickListener {
            findNavController().navigate(R.id.action_produseFragment_to_adaugaProdus)
        }


        produseViewModel.text.observe(viewLifecycleOwner, Observer {
           // binding.textProduse.text = it


        })
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}