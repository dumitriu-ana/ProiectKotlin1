package com.example.licenta2.ui.clienti.adaugare_client

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.licenta2.databinding.FragmentAdaugaClientBinding
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AdaugaClientFragment : Fragment() {
    private  lateinit var adaugaClientViewModel: AdaugaClientViewModel
    private var _binding: FragmentAdaugaClientBinding? = null;
    private val binding get() = _binding!!




    private lateinit var appDatabase: AppDatabase

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

         adaugaClientViewModel = ViewModelProvider(this).get(AdaugaClientViewModel::class.java)
        _binding = FragmentAdaugaClientBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adaugaClientViewModel.initDatabase(requireContext())

        adaugaClientViewModel.text.observe(viewLifecycleOwner, Observer {
           // binding.textAdaugaClient.text = it

            appDatabase = AppDatabase.getDatabase(requireContext())




            binding.butonSalvareClient.setOnClickListener {
                writeData()
            }


        })
        return root
    }

    fun writeData()
    {
        //CIF-ul (Codul de Identificare Fiscală) este un număr unic de identificare a unei firme, format din 2 litere și 8 cifre, care este utilizat în România și în alte țări pentru a identifica firmele și pentru a monitoriza plățile fiscale.
        val cif = binding.editTextCIF.text.toString()
        val denumire = binding.editTextDenumireClient.text.toString()
        val regCom = binding.editTextRegCom.text.toString()
// identitatea și activitatea fiecărei firme, cum ar fi denumirea, adresa sediului social, obiectul de activitate, forma juridică, capitalul social, numele administratorilor, etc.
//Atributul Registrul Comerțului pentru o firmă reprezintă numărul de înregistrare la acest registru, care este atribuit de ONRC la înregistrarea firmei. Acest număr este unic și îl identifică pe fiecare agent economic în Registrul Comerțului.
        val platitorDeTVA = binding.checkBoxTVA.isChecked

        val localitate = binding.editTextLocalitate.text.toString()
        val judet = binding.editTextJudet.text.toString()
        val adresa = binding.editTextAdresa.text.toString()

        val nume = binding.editTextNume.text.toString()
        val telefon = binding.editTextTelefon.text.toString()
        val email = binding.editTextEmail.text.toString()

        if (cif.isNotEmpty() && denumire.isNotEmpty()) {
            val client = Client(cif = cif, denumire = denumire, regCom = regCom, platitorDeTVA= platitorDeTVA,
            localitate = localitate,judet = judet,adresa=adresa,nume = nume,telefon = telefon, email=email)

            adaugaClientViewModel.insertClient(client)

            binding.editTextCIF.text.clear()
            binding.editTextDenumireClient.text.clear()
            binding.editTextRegCom.text.clear()

            binding.checkBoxTVA.isChecked

            binding.editTextLocalitate.text.toString()
            binding.editTextJudet.text.toString()
            binding.editTextAdresa.text.toString()

            binding.editTextNume.text.toString()
            binding.editTextTelefon.text.toString()
            binding.editTextEmail.text.toString()
            Toast.makeText(requireContext(), "Clinet adăugat cu succes!", Toast.LENGTH_SHORT).show()

        }
        else
        {
            Toast.makeText(requireContext(), "no!", Toast.LENGTH_SHORT).show()

        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}