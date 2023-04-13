package com.example.licenta2.ui.incasari.adaugare_incasare

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.licenta2.R
import com.example.licenta2.databinding.FragmentAdaugaClientBinding
import com.example.licenta2.databinding.FragmentAdaugaIncasareBinding
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Incasare
import com.example.licenta2.ui.clienti.adaugare_client.AdaugaClientViewModel
import java.util.*

class AdaugaIncasare : Fragment() {
    private  lateinit var adaugaIncasareViewModel: AdaugaIncasareViewModel
    private var _binding: FragmentAdaugaIncasareBinding? = null;
    private val binding get() = _binding!!

    private lateinit var appDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        adaugaIncasareViewModel = ViewModelProvider(this).get(AdaugaIncasareViewModel::class.java)
        _binding = FragmentAdaugaIncasareBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adaugaIncasareViewModel.text.observe(viewLifecycleOwner, Observer {
           // binding.textAdaugaIncasare.text = it
        })

        adaugaIncasareViewModel.initDatabase(requireContext())
        appDatabase = AppDatabase.getDatabase(requireContext())


        val spinnerTipDocument = root.findViewById<Spinner>(R.id.spinnerTipDocument)
        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinnerTipDocument_items,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTipDocument.adapter = adapter

        spinnerTipDocument.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                //Toast.makeText(this@AdaugaProdus, "Selected: $selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }


        val dataIncasareEditText = root.findViewById<EditText>(R.id.editTextDataIncasare)

        dataIncasareEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Creeaza si afiseaza DatePickerDialog
            val datePickerDialog = DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
                val selectedDate = "${dayOfMonth}/${month+1}/${year}"
                dataIncasareEditText.setText(selectedDate)
            }, year, month, day)
            datePickerDialog.show()
        }
        binding.butonSalvareIncasare.setOnClickListener {
            writeData()
        }

        return root
    }

    private fun writeData() {
        val numarIncasare = binding.editTextNumarIncasare.text.toString()
        val client = binding.autoCompleteNumeClient.text.toString()
        val tipDocument = binding.spinnerTipDocument.selectedItem.toString()
        val valoare = binding.editTextValoareIncasare.text.toString()
        val dataIncasare = binding.editTextDataIncasare.text.toString()
        val reprezentand = binding.editTextReprezentandIncasare.text.toString()

        if(numarIncasare.isNotEmpty())
        {
            val incasare = Incasare(
                numarIncasare = numarIncasare.toInt(),
                clientIncasare = client,
                tipDocument = tipDocument,
                valoare = valoare.toDouble(),
                dataIncasare = dataIncasare,
                reprezentand = reprezentand
            )
            adaugaIncasareViewModel.insertIncasare(incasare)
            Toast.makeText(requireContext(), "incasare", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(requireContext(), "ohh nooo", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}