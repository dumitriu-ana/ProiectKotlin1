package com.example.licenta2.ui.incasari.adaugare_incasare

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
import com.example.licenta2.databinding.FragmentAdaugaIncasareBinding
import com.example.licenta2.ui.clienti.adaugare_client.AdaugaClientViewModel
import java.util.*

class AdaugaIncasare : Fragment() {
    private  lateinit var adaugaIncasareViewModel: AdaugaIncasareViewModel
    private var _binding: FragmentAdaugaIncasareBinding? = null;
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val adaugaIncasareViewModel = ViewModelProvider(this).get(AdaugaIncasareViewModel::class.java)
        _binding = FragmentAdaugaIncasareBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adaugaIncasareViewModel.text.observe(viewLifecycleOwner, Observer {
            binding.textAdaugaIncasare.text = it
        })

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

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}