package com.example.licenta2.ui.clienti.adaugare_client

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.example.licenta2.databinding.FragmentAdaugaClientBinding
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Client
import com.example.licenta2.ui.clienti.ClientDetaliiFragmentArgs
import com.example.licenta2.ui.showSnackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import android.Manifest
import android.widget.Toast
import androidx.core.app.ActivityCompat


class AdaugaClientFragment : Fragment() {
    private  lateinit var adaugaClientViewModel: AdaugaClientViewModel

    private val navigationArgs: ClientDetaliiFragmentArgs by navArgs()
    lateinit var client: Client
    private var clientId: Int = 0
    private var imagePath:String = ""
    private var _binding: FragmentAdaugaClientBinding? = null;
    private val binding get() = _binding!!

    private val RESULT_LOAD_IMAGE=1
    private val REQUEST_CODE_PERMISSIONS = 2





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

            //img

            binding.btnAdaugaImagineClient.setOnClickListener {
                if (ContextCompat.checkSelfPermission(requireContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    // Permisiunea a fost deja acordată, deschideți galeria
                    openImageGallery()
                } else {
                    // Permisiunea nu a fost acordată, solicitați-o utilizatorului
                    requestStoragePermission()
                }
            }


        })
        return root
    }

    private fun openImageGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent, RESULT_LOAD_IMAGE)
    }

    private fun requestStoragePermission() {
        val permissions = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
        ActivityCompat.requestPermissions(
            requireActivity(),
            permissions,
            REQUEST_CODE_PERMISSIONS
        )
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
                grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                // Permisiunile au fost acordate, deschideți galeria
                openImageGallery()
            } else {
                Toast.makeText(
                    requireContext(),
                    "Permisiunile de acces la stocarea externă au fost refuzate.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null) {
            val selectedImage: Uri? = data.data
            imagePath = selectedImage?.toString() ?: ""
            binding.imgViewClient.setImageURI(selectedImage)
        }
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
        if(!checkJudet(localitate.text.toString())){
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
                nume = nume.text.toString(),telefon = telefon.text.toString(), email=email.text.toString(), imagePath = imagePath)

            adaugaClientViewModel.insertClient(client)

            Toast.makeText(requireContext(), client.imagePath, Toast.LENGTH_LONG).show()

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = navigationArgs.clientId
        if (id > 0) {
            adaugaClientViewModel.retrieveItem(id).observe(this.viewLifecycleOwner) { selectedItem ->
                client = selectedItem
                bindEditText(client)
            }
        } else {
            binding.butonSalvareClient.setOnClickListener {
                adaugaClientViewModel.insertClient(client)
            }
        }
    }

    private fun bindEditText(client: Client?) {
        binding.apply {
            editTextCIF.setText(client!!.cif, TextView.BufferType.SPANNABLE)
            editTextDenumireClient.setText(client!!.denumire, TextView.BufferType.SPANNABLE)
            editTextCUIRegCom.setText(client!!.regCom, TextView.BufferType.SPANNABLE)
            editTextLocalitate.setText(client!!.localitate, TextView.BufferType.SPANNABLE)
            editTextJudet.setText(client!!.judet, TextView.BufferType.SPANNABLE)
            editTextAdresa.setText(client!!.adresa, TextView.BufferType.SPANNABLE)
            editTextNume.setText(client!!.nume, TextView.BufferType.SPANNABLE)
            editTextTelefon.setText(client!!.telefon, TextView.BufferType.SPANNABLE)
            editTextEmail.setText(client!!.email, TextView.BufferType.SPANNABLE)


            butonSalvareClient.setOnClickListener {
                updateClient()
            }
        }
    }

    private fun updateClient() {
        adaugaClientViewModel.updateClient(
            navigationArgs.clientId,
            binding.editTextCIF.text.toString(),
                    binding.editTextDenumireClient.text.toString(),
                    binding.editTextCUIRegCom.text.toString(),


                    binding.checkBoxTVA.isChecked,

                    binding.editTextLocalitate.text.toString(),
                    binding.editTextJudet.text.toString(),
                    binding.editTextAdresa.text.toString(),

                    binding.editTextNume.text.toString(),
                    binding.editTextTelefon.text.toString(),
                    binding.editTextEmail.text.toString(),
                    binding.imgViewClient.toString()


        )
val action = AdaugaClientFragmentDirections.actionAdaugaClientFragmentToClientFragment()
        findNavController().navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}