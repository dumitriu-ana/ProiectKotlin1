package com.example.licenta2.ui.incasari.adaugare_incasare

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
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
import com.example.licenta2.ui.showSnackbar
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*


fun currentDate(): String {
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return LocalDate.now().format(dateFormatter)
}

fun tipCard(tip: String):Boolean{
    return (tip == "Card" || tip == "CEC" || tip == "Card online")
    return false
}

fun checkDocumentNumber(documentNumber: String, documentType: String): Boolean {
    val regex: Regex = when (documentType) {
        "Chitanta" -> Regex("^\\d{13}/\\d{4}/\\d{2}\$")
        "Ordin de plata" -> Regex("^\\d{5,17}\$")
        "Mandat postal" -> Regex("^\\d{10}\$")
        "Card" -> Regex("^\\d{16}\$")
        "CEC" -> Regex("^\\d{8}\$")
        "Bilet de ordin" -> Regex("^\\d{10}\$")
        "Alta incasare" -> Regex("^\\w{2}\\d{2,20}\$")
        "Card online" -> Regex("^\\d{6}\\.\\d{6}\\.\\d{4}\\.\\d{4}\$")
        "Extras de cont" -> Regex("^\\d{4}\\.\\d{4}\\.\\d{4}\$")
        "Ramburs" -> Regex("^\\d{10}\$")
        else -> return false // tip de document nevalid
    }
    return regex.matches(documentNumber)
}
fun exemplePentruNrDoc(documentType: String): String {
    return when (documentType) {
        "Chitanta" -> "1234567890123/2022/01"
        "Ordin de plata" -> "123456"
        "Mandat postal" -> "1234567890"
        "Card" -> "1234567890123456"
        "CEC" -> "12345678"
        "Bilet de ordin" -> "1234567890"
        "Alta incasare"->"1234567890"
        "Card online" -> "123456.123456.1234.1234"
        "Extras de cont" -> "1234.1234.1234"
        "Ramburs" -> "1234567890"
        else -> "" // tip de document care nu exista
    }
}

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

        var nrdoc = binding.editTextNumarIncasare

        var selectedItem =""
        spinnerTipDocument.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedItem = parent.getItemAtPosition(position).toString()

                if (tipCard(selectedItem))
                {
                    binding.textViewNumarIncasare.text = "Cont Bancar"
                   // nrdoc.error = "Introdu un nr. doc. valid \n (exemplu: ${exemplePentruNrDoc(selectedItem)} )"

                }
                else{
                    binding.textViewNumarIncasare.text = "Numar document"

                }
                val str = exemplePentruNrDoc(selectedItem)
               // Toast.makeText(requireContext(), str , Toast.LENGTH_LONG).show()
                nrdoc.hint = exemplePentruNrDoc(selectedItem)
               // nrdoc.error = "Introdu un nr. doc. valid \n (exemplu: ${exemplePentruNrDoc(selectedItem)} )"
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        }


//        if(!checkDocumentNumber(nrdoc.text.toString(), selectedItem))
//        {
//            nrdoc.error = "Introdu un nr. doc. valid \n (exemplu: ${exemplePentruNrDoc(selectedItem)} )"
//
//        }

        var dataIncasareEditText = root.findViewById<EditText>(R.id.editTextDataIncasare)
        dataIncasareEditText.text = Editable.Factory.getInstance().newEditable(currentDate())

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

        val tip = spinnerTipDocument.selectedItem.toString()
      //  Toast.makeText(requireContext(), "tip + $tip", Toast.LENGTH_LONG).show()
        return root
    }

    private fun writeData() {
        var valid = true

        val client = binding.autoCompleteNumeClient
        if(client.text.toString().isBlank()){
            client.error = "Selecteaza client"
            valid = false
        }
        val tipDocument = binding.spinnerTipDocument.selectedItem.toString()
        val numarIncasare = binding.editTextNumarIncasare
        if(!checkDocumentNumber(numarIncasare.text.toString(), tipDocument))
        {
            numarIncasare.error = "Introdu un nr. doc. valid \n (exemplu: ${exemplePentruNrDoc(tipDocument)} )"
            valid = false
        }


        val dataIncasare = binding.editTextDataIncasare
        if (dataIncasare.text.toString().isBlank()) {
            dataIncasare.error = "Adauga data"
            valid = false
        }


        val valoare = binding.editTextValoareIncasare
        if(valoare.text.toString().isBlank() || valoare.text.toString().toDouble()==0.0)
        {
            valoare.error = "Adauga valoarea incasarii"
            valid = false
        }


        val reprezentand = binding.editTextReprezentandIncasare

        Toast.makeText(requireContext(), "valid $valid" + numarIncasare.text.toString() + client.text.toString(), Toast.LENGTH_LONG).show()
        if(valid) {
            val incasare = Incasare(
                numarIncasare = numarIncasare.text.toString(),
                clientIncasare = client.text.toString(),
                tipDocument = tipDocument,
                valoare = valoare.text.toString().toDouble(),
                dataIncasare = dataIncasare.text.toString(),
                reprezentand = reprezentand.text.toString()
            )
            adaugaIncasareViewModel.insertIncasare(incasare)
            // Toast.makeText(requireContext(), valid, Toast.LENGTH_SHORT).show()
            view?.let { showSnackbar(requireContext(), it, "Incasare introdusa cu succes!", false) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}