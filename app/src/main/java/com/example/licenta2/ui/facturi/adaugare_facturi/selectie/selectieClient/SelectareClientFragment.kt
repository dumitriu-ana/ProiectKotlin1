package com.example.licenta2.ui.facturi.adaugare_facturi.selectie

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.licenta2.R
import com.example.licenta2.databinding.FragmentSelectareClientBinding
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Client
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.licenta2.ui.facturi.adaugare_facturi.CellClickListener
import com.example.licenta2.ui.facturi.adaugare_facturi.selectie.selectieClient.FClientAdaptor
import com.example.licenta2.ui.facturi.adaugare_facturi.selectie.selectieClient.SelectareClientViewModel





class SelectareClientFragment : Fragment(), CellClickListener {

    private lateinit var selectareClientViewModel: SelectareClientViewModel
    private var _binding: FragmentSelectareClientBinding? = null;
    private val binding get() = _binding!!

    private lateinit var appDatabase: AppDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var clientAdaptor: FClientAdaptor



    //sharedvm
    private val sharedViewModel: SharedViewModel by activityViewModels()


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
        clientAdaptor = FClientAdaptor(requireContext(), emptyList(), this)

        recyclerView.adapter = clientAdaptor
        selectareClientViewModel.getAllClienti().observe(viewLifecycleOwner, Observer { clientList ->
            val clientListNormal: List<Client> = clientList
            clientAdaptor = FClientAdaptor(requireContext(), clientListNormal, this)
            recyclerView.adapter = clientAdaptor
        })



      //  val fragment = requireActivity().supportFragmentManager.findFragmentByTag("")



        return root

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCellClickListener(data: Client) {
       sharedViewModel.selectareClient(data)
        findNavController().popBackStack()
    }

}