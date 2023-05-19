package com.example.licenta2.ui.setari

import android.content.res.Configuration
import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.licenta2.MySharedPreferences
import com.example.licenta2.R
import com.example.licenta2.databinding.FragmentFacturiBinding
import com.example.licenta2.databinding.FragmentSetariBinding
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.ui.facturi.FacturiViewModel
import java.util.*


class SetariFragment : Fragment() {

    private lateinit var setariViewModel: SetariViewModel
    private var _binding: FragmentSetariBinding? = null;
    private val binding get() = _binding!!

    private lateinit var appDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setariViewModel = ViewModelProvider(this).get(SetariViewModel::class.java)
       _binding =  FragmentSetariBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setariViewModel.initDatabase(requireContext())


      binding.btnSetariStergereIncasari.setOnClickListener {
            setariViewModel.stergereIncasari()
            Toast.makeText(requireContext(), "incasari sterse", Toast.LENGTH_LONG).show()
        }
        binding.btnSetariStergereClienti.setOnClickListener{
            setariViewModel.stergereClienti()
        }
        binding.btnSetariStergereFacturi.setOnClickListener {
            setariViewModel.stergereFacturi()
        }
        binding.btnSetariStergereProduse.setOnClickListener {
            setariViewModel.stergereProduse()
        }


        val mySharedPreferences = MySharedPreferences(requireContext())

        binding.switchDarkTheme.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                mySharedPreferences.setDarkThemeEnabled(true)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                mySharedPreferences.setDarkThemeEnabled(false)
            }
        }

        val languages = arrayOf("Română", "English", "Français")

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, languages)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerLimba.adapter = adapter


        binding.spinnerLimba.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedLanguage = when (position) {
                    0 -> "ro" //  română
                    1 -> "en" //  engleză
                    2 -> "fr" //  franceză
                    else -> "ro" //  implicită
                }

                // in shared pf
                mySharedPreferences.setLanguage(selectedLanguage)
                val locale = Locale(selectedLanguage)
                Locale.setDefault(locale)
                val resources = resources
                val configuration = resources.configuration
                configuration.setLocale(locale)
                resources.updateConfiguration(configuration, resources.displayMetrics)
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        return root



    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}