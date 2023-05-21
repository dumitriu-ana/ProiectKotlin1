package com.example.licenta2.ui.clienti

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.licenta2.R
import com.example.licenta2.databinding.FragmentClientDetaliiBinding
import com.example.licenta2.databinding.FragmentClientiBinding
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Client
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class ClientDetaliiFragment : Fragment() {
    private lateinit var clientiViewModel: ClientiViewModel
    private val navigationArgs: ClientDetaliiFragmentArgs by navArgs()
    lateinit var client: Client

    private var clientId: Int = 0

    private var _binding: FragmentClientDetaliiBinding? = null;
    private val binding get() = _binding!!

   private lateinit var appDatabase: AppDatabase


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?):
            View? {
        clientiViewModel = ViewModelProvider(this).get(ClientiViewModel::class.java)
        _binding = FragmentClientDetaliiBinding.inflate(inflater, container, false)
        val root: View = binding.root

        clientiViewModel.initDatabase(requireContext())

//        arguments?.let {
//            val safeArgs = ClientDetaliiFragmentArgs.fromBundle(it)
//            clientId = safeArgs.clientId
//        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.clientId
        clientiViewModel.retrieveItem(id).observe(this.viewLifecycleOwner) { selectedClient ->
            client = selectedClient
            completareTV(client)
        }
        // Retrieve the item details using the itemId.
        // Attach an observer on the data (instead of polling for changes) and only update the
        // the UI when the data actually changes.

        }

    private fun completareTV(client: Client?) {
        binding.apply {
            editTextCIF.text = client!!.cif
            editTextDenumireClient.text = client.denumire
            editTextCUIRegCom.text = client.regCom
            editTextLocalitate.text = client.localitate
            editTextJudet.text = client.judet
            editTextAdresa.text = client.adresa
            editTextNume.text = client.nume
            editTextTelefon.text = client.telefon
            editTextEmail.text = client.email
            if(!client.platitorDeTVA!!)
            {
                checkBoxTVA.text = "Nu este platitor de tva"
            }

            btnStergereClient.setOnClickListener { showConfirmationDialog() }

            fabEditClient.setOnClickListener {
                val action = ClientDetaliiFragmentDirections.actionClientDetaliiFragment2ToAdaugaClientFragment(
                    "Editare client",
                        client.idClient
                )
                findNavController().navigate(action)
            }


//            sellItem.setOnClickListener { viewModel.sellItem(item) }
//            deleteItem.setOnClickListener { showConfirmationDialog() }
//            editItem.setOnClickListener { editItem() }
        }
    }

    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage("Dorești să ștergi acest client?")
            .setCancelable(false) // Setează cancelabil la false pentru a preveni închiderea dialogului la apăsarea în afara acestuia
            .setNegativeButton("Nu") { dialog, _ ->
                // Acțiunea care trebuie executată când se apasă butonul "Nu"
                dialog.dismiss() // Închide dialogul
            }
            .setPositiveButton("Da") { dialog, _ ->
                // Acțiunea care trebuie executată când se apasă butonul "Da"
                deleteClient()
                dialog.dismiss() // Închide dialogul
            }
            .show()
    }

    private fun deleteClient() {
        clientiViewModel.deleteItem(client)
          findNavController().navigateUp()
    }


//    private fun showConfirmationDialog() {
//        MaterialAlertDialogBuilder(requireContext())
//            .setTitle(getString(android.R.string.dialog_alert_title))
//            .setMessage(getString(R.string.delete_question))
//            .setCancelable(false)
//            .setNegativeButton(getString(R.string.no)) { _, _ -> }
//            .setPositiveButton(getString(R.string.yes)) { _, _ ->
//                deleteItem()
//            }
//            .show()
//    }
}


