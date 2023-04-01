package com.example.licenta2.ui.facturi.adaugare_facturi

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.licenta2.R
import com.example.licenta2.databinding.FragmentAdaugaClientBinding
import com.example.licenta2.databinding.FragmentAdaugaFacturaBinding
import com.example.licenta2.ui.clienti.adaugare_client.AdaugaClientViewModel
import java.text.SimpleDateFormat
import java.util.*

class AdaugaFactura : Fragment() {
    private  lateinit var adaugaFacturaViewModel: AdaugaFacturaViewModel
    private var _binding: FragmentAdaugaFacturaBinding? = null;
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val adaugaFacturaViewModel = ViewModelProvider(this).get(AdaugaFacturaViewModel::class.java)
        _binding = FragmentAdaugaFacturaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adaugaFacturaViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textAdaugaFactura.text = it

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
        })
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}