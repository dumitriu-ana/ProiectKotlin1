package com.example.licenta2.ui.facturi.adaugare_facturi.selectie

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.licenta2.R
import com.example.licenta2.databinding.FragmentClientiBinding
import com.example.licenta2.databinding.FragmentSelectareClientBinding
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Client
import com.example.licenta2.ui.clienti.ClientAdaptor
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.licenta2.ui.clienti.ClientiViewModel

class SelectareClientFragment : Fragment() {

    private lateinit var selectareClientViewModel: SelectareClientViewModel
    private var _binding: FragmentSelectareClientBinding? = null;
    private val binding get() = _binding!!

    private lateinit var appDatabase: AppDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var clientAdaptor: ClientAdaptor

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        selectareClientViewModel = ViewModelProvider(this).get(SelectareClientViewModel::class.java)
        _binding = FragmentSelectareClientBinding.inflate(inflater, container, false)
        val root: View = binding.root

        selectareClientViewModel.initDatabase(requireContext())

        // RV
        recyclerView = binding.rvSelectareClient
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        clientAdaptor = ClientAdaptor(requireContext(), emptyList())
        recyclerView.adapter = clientAdaptor
        selectareClientViewModel.getAllClienti().observe(viewLifecycleOwner, Observer { clientList ->
            val clientListNormal: List<Client> = clientList
            clientAdaptor = ClientAdaptor(requireContext(), clientListNormal)
            recyclerView.adapter = clientAdaptor
        })

        selectareClientViewModel.text.observe(viewLifecycleOwner, Observer {
            appDatabase = AppDatabase.getDatabase(requireContext())
        })
        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}