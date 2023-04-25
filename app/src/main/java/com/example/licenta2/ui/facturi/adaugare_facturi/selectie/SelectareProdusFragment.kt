package com.example.licenta2.ui.facturi.adaugare_facturi.selectie

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.licenta2.R
import com.example.licenta2.databinding.FragmentAdaugaProdusBinding
import com.example.licenta2.databinding.FragmentFacturiBinding
import com.example.licenta2.databinding.FragmentSelectareProdusBinding
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Factura
import com.example.licenta2.persistence.entities.Produs
import com.example.licenta2.ui.facturi.FacturaAdaptor
import com.example.licenta2.ui.facturi.FacturiViewModel
import com.example.licenta2.ui.produse.ProdusAdaptor

class SelectareProdusFragment : Fragment() {
    private lateinit var selectareProdusViewModel: SelectareProdusViewModel
    private var _binding: FragmentSelectareProdusBinding? = null;
    private val binding get() = _binding!!

    private lateinit var appDatabase: AppDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var produsAdaptor: ProdusAdaptor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        selectareProdusViewModel = ViewModelProvider(this).get(SelectareProdusViewModel::class.java)
        _binding = FragmentSelectareProdusBinding.inflate(inflater, container, false)
        val root: View = binding.root


        selectareProdusViewModel.initDatabase(requireContext())

        // RV
          recyclerView = binding.rvSelectareProdus
          recyclerView.layoutManager = LinearLayoutManager(requireContext())
         produsAdaptor = ProdusAdaptor(requireContext(), emptyList())
        recyclerView.adapter = produsAdaptor
        selectareProdusViewModel.getAllProduse().observe(viewLifecycleOwner, Observer { produseList ->
            val produseListNormal: List<Produs> = produseList
            produsAdaptor = ProdusAdaptor(requireContext(), produseListNormal)
            recyclerView.adapter = produsAdaptor
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}