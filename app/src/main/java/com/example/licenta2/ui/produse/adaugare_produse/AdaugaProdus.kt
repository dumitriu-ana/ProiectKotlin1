package com.example.licenta2.ui.produse.adaugare_produse

import android.content.Context
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
import com.example.licenta2.ui.showSnackbar
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch



fun checkDenuire(name: String): Boolean {
    val regex = Regex("^[a-zA-Z0-9 ]+$")
    return regex.matches(name)
}
fun checkUM(um: String): Boolean {
    return um.isNotBlank() && um.matches(Regex("^[a-zA-Z\\s]+$"))
}


//fun showSnackbar(context: Context, view: View, message: String, isError: Boolean) {
//    val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
//    val snackbarView = snackbar.view
//    if (isError) {
//        snackbarView.setBackgroundColor(ContextCompat.getColor(context, R.color.red))
//    } else {
//        snackbarView.setBackgroundColor(ContextCompat.getColor(context, R.color.green))
//    }
//    snackbar.show()
//}


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
        var valid=true
        //val denumire = binding.editTextDenumireProdus.text.toString()
        val campDenumire = binding.editTextDenumireProdus
        if(!checkDenuire(campDenumire.text.toString()))
        {
            campDenumire.error = "Denumirea trebuie completata"
            valid = false
        }
        val unitateDeMasura = binding.editTextUnitateMasura
        //val unitateDeMasura = binding.editTextUnitateMasura.text.toString()
        if(!checkUM(unitateDeMasura.text.toString()))
        {
            unitateDeMasura.error = "Adauga unitate de masura"
            valid = false
        }
        val pret = binding.editTextPret
       // val pret = binding.editTextPret.text.toString()
        if(pret.text.toString().isBlank() || pret.text.toString().toDouble()==0.0)
        {
            pret.error = "Adauga pretul produsului"
            valid = false
        }

        val cotaTVA = binding.spinnerCotaTVA.selectedItem.toString()
        val contineTVA = binding.checkBoxContineTVA.isChecked
        if (valid) {
            val produs = Produs(
                denumire = campDenumire.text.toString(),
                unitateDeMasura = unitateDeMasura.text.toString(),
                pret = pret.text.toString().toDouble(),
                cotaTVA = cotaTVA.toDouble(),
                contineTVA = contineTVA
            )
            adaugaProdusViewModel.insertProdus(produs)
            binding.editTextDenumireProdus.text.clear()
            binding.editTextUnitateMasura.text.clear()
            binding.editTextPret.text.clear()
           // Toast.makeText(requireContext(), valid, Toast.LENGTH_SHORT).show()
            view?.let { showSnackbar(requireContext(), it, "Produs introdus cu succes!", false) }

        }
    }

    fun readData() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
