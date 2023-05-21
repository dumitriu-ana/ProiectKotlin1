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
import com.example.licenta2.databinding.FragmentClientiBinding
import com.example.licenta2.persistence.database.AppDatabase

class ClientiFragment : Fragment(), ClientAdaptor.OnItemClickListener {
    private lateinit var clientiViewModel: ClientiViewModel
    private var _binding: FragmentClientiBinding? = null
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

        // Initialize RecyclerView
        recyclerView = binding.rvClienti
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        clientAdaptor = ClientAdaptor(requireContext(), emptyList())
        recyclerView.adapter = clientAdaptor

        clientAdaptor.setOnItemClickListener(this)

        clientiViewModel.getAllClienti().observe(viewLifecycleOwner, Observer { clientList ->
            clientAdaptor.clients = clientList
            clientAdaptor.notifyDataSetChanged()
        })

        binding.butonAdaugaClient.setOnClickListener {
            val action = ClientiFragmentDirections.actionClientiFragmentToAdaugaClientFragment("Adaugare Client")

            findNavController().navigate(action)
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

    override fun onItemClick(client_id: Int) {
        val action = ClientiFragmentDirections.actionClientiFragmentToClientDetaliiFragment2(client_id)
        findNavController().navigate(action)
    }
}
