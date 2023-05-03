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
import androidx.navigation.fragment.findNavController
import com.example.licenta2.databinding.FragmentAdaugaClientBinding
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Client
import com.example.licenta2.ui.showSnackbar
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

    fun checkCif(input: String): Boolean {
        val pattern = "^RO\\d{8}\$".toRegex()
        return pattern.matches(input)
    }

    fun checkDenumire(input: String): Boolean {
        val regex = Regex("[a-zA-Z0-9][a-zA-Z0-9\\s\\.-]*[a-zA-Z0-9]$")
        return regex.matches(input) && input.isNotBlank()
    }

    fun checkCUI(input: String): Boolean {
        return input.matches(Regex("^\\d{10}\$"))
    }
    fun checkStringLitere(input: String): Boolean {
        return input.isBlank() || input.matches(Regex("[a-zA-Z ]+"))
    }

    fun checkTelefon(input: String): Boolean {
        return input.isBlank() || input.matches(Regex("07[0-9]{8}"))
    }
    fun checkMail(email: String): Boolean {
        val emailRegex = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
        return email.isBlank() || emailRegex.matches(email)
    }
    fun checkJudet(input: String): Boolean {
        val regex = Regex("^[a-zA-Z][a-zA-Z0-9\\s-.,]*\$")
        return regex.matches(input.trim())
    }



    fun writeData()
    {
        var valid = true

        //CIF-ul -anaf (Codul de Identificare Fiscală) este un număr unic de identificare a unei firme, format din 2 litere și 8 cifre, care este utilizat în România și în alte țări pentru a identifica firmele și pentru a monitoriza plățile fiscale.
        val cif = binding.editTextCIF
        if(!checkCif(cif.text.toString())) {
            cif.error = "CIF invalid"
            valid = false
        }

        val denumire = binding.editTextDenumireClient
        if(!checkDenumire(denumire.text.toString())){
            denumire.error = "Denumire invalida"
            valid = false
        }

        val regCom = binding.editTextCUIRegCom
        if(!checkCUI(regCom.text.toString()))
        {
            regCom.error = "cui invalid"
            valid = false
        }
        // -onrc -10 cifre identitatea și activitatea fiecărei firme, cum ar fi denumirea, adresa sediului social, obiectul de activitate, forma juridică, capitalul social, numele administratorilor, etc.
//Atributul Registrul Comerțului pentru o firmă reprezintă numărul de înregistrare la acest registru, care este atribuit de ONRC la înregistrarea firmei. Acest număr este unic și îl identifică pe fiecare agent economic în Registrul Comerțului.

        val platitorDeTVA = binding.checkBoxTVA.isChecked

        val localitate = binding.editTextLocalitate
        if(!checkStringLitere(localitate.text.toString())){
            localitate.error = "Localitate invalida"
            valid = false
        }
        val judet = binding.editTextJudet
        if(!checkJudet(judet.text.toString()))
        {
            judet.error = "Numele judetului trebuie sa contina exclusiv litere"
            valid = false
        }

        //val localitate = binding.editTextLocalitate.text.toString()

        val adresa = binding.editTextAdresa
        if(!checkJudet(adresa.text.toString()))
        {
            adresa.error="Adresa nu este valida"
            valid = false
        }

        val nume = binding.editTextNume
        if(!checkStringLitere(nume.text.toString()))
        {
            nume.error = "Numele trebuie sa contina litere"
            valid = false
        }

        val telefon = binding.editTextTelefon
        if(!checkTelefon(telefon.text.toString()))
        {
            telefon.error = "Numarul de telefon este invalid"
            valid = false
        }
        val email = binding.editTextEmail
        if(!checkMail(email.text.toString()))
        {
            email.error = "Email invalid"
            valid = false
        }

        if (valid) {
            val client = Client(cif = cif.text.toString(), denumire = denumire.text.toString(), regCom = regCom.text.toString(),
                platitorDeTVA= platitorDeTVA,
            localitate = localitate.text.toString(),judet = judet.text.toString(),adresa=adresa.text.toString(),
                nume = nume.text.toString(),telefon = telefon.text.toString(), email=email.text.toString())

            adaugaClientViewModel.insertClient(client)

            binding.editTextCIF.text.clear()
            binding.editTextDenumireClient.text.clear()
            binding.editTextCUIRegCom.text.clear()

            binding.checkBoxTVA.isChecked

            binding.editTextLocalitate.text.clear()
            binding.editTextJudet.text.clear()
            binding.editTextAdresa.text.clear()

            binding.editTextNume.text.clear()
            binding.editTextTelefon.text.clear()
            binding.editTextEmail.text.clear()
           // Toast.makeText(requireContext(), valid, Toast.LENGTH_SHORT).show()
            view?.let { showSnackbar(requireContext(), it, "Client introdus cu succes!", false) }

            findNavController().popBackStack()
        }


    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}