package com.example.licenta2.ui.facturi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.licenta2.R
import com.example.licenta2.databinding.FragmentFacturiBinding
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Factura


class FacturiFragment : Fragment() {
    private lateinit var facturiViewModel: FacturiViewModel
    private var _binding: FragmentFacturiBinding? = null;
    private val binding get() = _binding!!

    private lateinit var appDatabase: AppDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var facturaAdaptor: FacturaAdaptor

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        facturiViewModel = ViewModelProvider(this).get(FacturiViewModel::class.java)
        _binding = FragmentFacturiBinding.inflate(inflater, container, false)
        val root: View = binding.root


        facturiViewModel.initDatabase(requireContext())

        // RV
        recyclerView = binding.rvFacturi
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        facturaAdaptor = FacturaAdaptor(requireContext(), emptyList())
        recyclerView.adapter = facturaAdaptor
        facturiViewModel.getAllFacturi().observe(viewLifecycleOwner, Observer { facturaList ->
            val facturaListNormal: List<Factura> = facturaList
            facturaAdaptor = FacturaAdaptor(requireContext(), facturaListNormal)
            recyclerView.adapter = facturaAdaptor
        })



        binding.butonAdaugaFactura.setOnClickListener {
            findNavController().navigate(R.id.action_facturiFragment_to_adaugaFactura)
        }
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