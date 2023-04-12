package com.example.licenta2.ui.produse.adaugare_produse

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.licenta2.R
import com.example.licenta2.databinding.FragmentAdaugaProdusBinding
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Produs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AdaugaProdus : Fragment() {
    private lateinit var adaugaProdusViewModel: AdaugaProdusViewModel
    private var _binding: FragmentAdaugaProdusBinding? = null;
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        adaugaProdusViewModel = ViewModelProvider(this).get(AdaugaProdusViewModel::class.java)
        _binding = FragmentAdaugaProdusBinding.inflate(inflater, container, false)
        val root: View = binding.root
        adaugaProdusViewModel.initDatabase(requireContext())

        adaugaProdusViewModel.text.observe(viewLifecycleOwner, Observer {
           // binding.textAdaugaProdus.text = it

            val spinnerCotaTVA = root.findViewById<Spinner>(R.id.spinnerCotaTVA)
            val adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.spinnerCotaTVA_items,
                android.R.layout.simple_spinner_item
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerCotaTVA.adapter = adapter

            spinnerCotaTVA.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

            //scriere in db
            binding.butonSalvareProdus.setOnClickListener {
                writeData()
            }
        })
        return root
    }


    fun writeData() {
        val denumire = binding.editTextDenumireProdus.text.toString()
        val unitateDeMasura = binding.editTextUnitateMasura.text.toString()
        val pret = binding.editTextPret.text.toString()

        val cotaTVA = binding.spinnerCotaTVA.selectedItem.toString()
        val contineTVA = binding.checkBoxContineTVA.isChecked

        if (denumire.isNotEmpty() && unitateDeMasura.isNotEmpty()) {
            val produs = Produs(
                denumire = denumire,
                unitateDeMasura = unitateDeMasura,
                pret = pret.toDouble(),
                cotaTVA = cotaTVA.toDouble(),
                contineTVA = contineTVA
            )
            adaugaProdusViewModel.insertProdus(produs)

            binding.editTextDenumireProdus.text.clear()
            binding.editTextUnitateMasura.text.clear()
            binding.editTextPret.text.clear()
            Toast.makeText(requireContext(), "Produs adăugat cu succes!", Toast.LENGTH_SHORT).show()

        } else {
            Toast.makeText(requireContext(), "no!", Toast.LENGTH_SHORT).show()

        }

    }

    fun readData() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
