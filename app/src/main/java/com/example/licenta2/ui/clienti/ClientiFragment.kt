package com.example.licenta2.ui.clienti

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
import com.example.licenta2.databinding.FragmentAcasaBinding
import com.example.licenta2.databinding.FragmentClientiBinding
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Client
import com.example.licenta2.persistence.entities.Factura
import com.example.licenta2.ui.acasa.AcasaViewModel

class ClientiFragment : Fragment() {
    private lateinit var clientiViewModel: ClientiViewModel
    private var _binding: FragmentClientiBinding? = null;
    private val binding get() = _binding!!

    private lateinit var appDatabase: AppDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var clientAdaptor: ClientAdaptor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        clientiViewModel = ViewModelProvider(this).get(ClientiViewModel::class.java)
        _binding = FragmentClientiBinding.inflate(inflater, container, false)
        val root: View = binding.root

        clientiViewModel.initDatabase(requireContext())

        // RV
        recyclerView = binding.rvClienti
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        clientAdaptor = ClientAdaptor(requireContext(), emptyList())
        recyclerView.adapter = clientAdaptor
        clientiViewModel.getAllClienti().observe(viewLifecycleOwner, Observer { clientList ->
            val clientListNormal: List<Client> = clientList
            clientAdaptor = ClientAdaptor(requireContext(), clientListNormal)
            recyclerView.adapter = clientAdaptor
        })
        binding.butonAdaugaClient.setOnClickListener {
            findNavController().navigate(R.id.action_clientiFragment_to_adaugaClientFragment)
        }
        clientiViewModel.text.observe(viewLifecycleOwner, Observer {
           // binding.textClienti.text = it
            appDatabase = AppDatabase.getDatabase(requireContext())
        })
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}