package com.example.licenta2.ui.facturi.adaugare_facturi

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
import com.example.licenta2.databinding.FragmentAdaugaFacturaBinding
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Factura
import java.util.*

class AdaugaFactura : Fragment() {
    private  lateinit var adaugaFacturaViewModel: AdaugaFacturaViewModel
    private var _binding: FragmentAdaugaFacturaBinding? = null;
    private val binding get() = _binding!!

    private lateinit var appDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        adaugaFacturaViewModel = ViewModelProvider(this).get(AdaugaFacturaViewModel::class.java)
        _binding = FragmentAdaugaFacturaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adaugaFacturaViewModel.initDatabase(requireContext())
        appDatabase = AppDatabase.getDatabase(requireContext())

        adaugaFacturaViewModel.text.observe(viewLifecycleOwner, Observer {
           // binding.textAdaugaFactura.text = it


            val spinnerClientFactura = root.findViewById<Spinner>(R.id.spinnerClientFactura)
            val adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.spinnerClientFactura_items,
                android.R.layout.simple_spinner_item
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerClientFactura.adapter = adapter

            spinnerClientFactura.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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


            val spinnerTermenDePlata = root.findViewById<Spinner>(R.id.spinnerTermenDePlata)
            val adapterTermenDePlata = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.spinnerTermenDePlata_items,
                android.R.layout.simple_spinner_item
            )
            adapterTermenDePlata.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerTermenDePlata.adapter = adapterTermenDePlata

            spinnerTermenDePlata.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

            val dateEditText = root.findViewById<EditText>(R.id.editTextData)
            val dataScadentaEditText = root.findViewById<EditText>(R.id.editTextDataScadenta)
            val dataLivrariiEditText = root.findViewById<EditText>(R.id.editTextDataLivrarii)
            val dataIncasariiEditText = root.findViewById<EditText>(R.id.editTextDataIncasarii)

            dataIncasariiEditText.setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                // Creeaza si afiseaza DatePickerDialog
                val datePickerDialog = DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
                    val selectedDate = "${dayOfMonth}/${month+1}/${year}"
                    dataIncasariiEditText.setText(selectedDate)
                }, year, month, day)
                datePickerDialog.show()
            }
            dataLivrariiEditText.setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                // Creeaza si afiseaza DatePickerDialog
                val datePickerDialog = DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
                    val selectedDate = "${dayOfMonth}/${month+1}/${year}"
                    dataLivrariiEditText.setText(selectedDate)
                }, year, month, day)
                datePickerDialog.show()
            }


            dataScadentaEditText.setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                // Creeaza si afiseaza DatePickerDialog
                val datePickerDialog = DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
                    val selectedDate = "${dayOfMonth}/${month+1}/${year}"
                    dataScadentaEditText.setText(selectedDate)
                }, year, month, day)
                datePickerDialog.show()
            }

            dateEditText.setOnClickListener {
                // Obtine data curenta
                val c = Calendar.getInstance()
                val year = c.get(Calendar.YEAR)
                val month = c.get(Calendar.MONTH)
                val day = c.get(Calendar.DAY_OF_MONTH)
                // Creeaza si afiseaza DatePickerDialog
                val datePickerDialog = DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
                    val selectedDate = "${dayOfMonth}/${month+1}/${year}"
                    dateEditText.setText(selectedDate)
                }, year, month, day)
                datePickerDialog.show()
            }



            binding.butonSalvareFactura.setOnClickListener {
                writeData()
            }
        })
        return root
    }

    private fun writeData() {
        val client = binding.spinnerClientFactura.selectedItem.toString()
        val adresaLivrare = binding.editTextAdresaFactura.text.toString()
        val data = binding.editTextData.text.toString()
        val serie = binding.editTextSerieFactura.text.toString()
        //lista prod
        //
        val termenDePlata = binding.spinnerTermenDePlata.selectedItem.toString()
        val dataScadenta = binding.editTextDataScadenta.text.toString()
        val dataLivrarii = binding.editTextDataLivrarii.text.toString()
        val dataIncasarii = binding.editTextDataIncasarii.text.toString()
        val intocmitDe = binding.editTextIntocmitDe.text.toString()
        val mentiuni = binding.editTextMentiuni.text.toString()

        if(client.isNotEmpty() && dataLivrarii.isNotEmpty())
        {

                val factura = Factura(
                client = client,
                adresaLivrare = adresaLivrare,
                data =data,
                serie =serie,
                //prod
                termenDePlata = termenDePlata,
                dataScadenta = dataScadenta,
                dataLivrarii = dataLivrarii,
                dataIncasarii = dataIncasarii,
                intocmitDe = intocmitDe,
                mentiuni = mentiuni
            )
            adaugaFacturaViewModel.insertFactura(factura)
            Toast.makeText(requireContext(), "factura adaugata", Toast.LENGTH_SHORT).show()
        } else{
            Toast.makeText(requireContext(), "hooopaa", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}