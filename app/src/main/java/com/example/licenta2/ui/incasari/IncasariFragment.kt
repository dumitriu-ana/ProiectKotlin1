package com.example.licenta2.ui.incasari

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
import com.example.licenta2.databinding.FragmentIncasariBinding
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Incasare
import com.example.licenta2.ui.facturi.FacturiViewModel


class IncasariFragment : Fragment() {
    private lateinit var incasariViewModel: IncasariViewModel
    private var _binding: FragmentIncasariBinding? = null;
    private val binding get() = _binding!!

    private lateinit var appDatabase: AppDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var incasareAdaptor: IncasareAdaptor
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        incasariViewModel = ViewModelProvider(this).get(IncasariViewModel::class.java)
        _binding = FragmentIncasariBinding.inflate(inflater, container, false)
        val root: View = binding.root

        incasariViewModel.initDatabase(requireContext())

        // RV
        recyclerView = binding.rvIncasare
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        incasareAdaptor = IncasareAdaptor(requireContext(), emptyList())
        recyclerView.adapter = incasareAdaptor
        incasariViewModel.getAllIncasari().observe(viewLifecycleOwner, Observer { incasareList ->
            val incasareListNormal: List<Incasare> = incasareList
            incasareAdaptor = IncasareAdaptor(requireContext(), incasareListNormal)
            recyclerView.adapter = incasareAdaptor
        })

        binding.butonAdaugaIncasare.setOnClickListener {
            findNavController().navigate(R.id.action_incasariFragment_to_adaugaIncasare)
        }
        incasariViewModel.text.observe(viewLifecycleOwner, Observer {
            // binding.textincasarei.text = it
            appDatabase = AppDatabase.getDatabase(requireContext())
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}