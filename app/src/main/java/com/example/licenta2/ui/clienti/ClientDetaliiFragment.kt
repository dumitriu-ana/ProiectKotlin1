package com.example.licenta2.ui.clienti

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.licenta2.databinding.FragmentClientDetaliiBinding
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Client
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class ClientDetaliiFragment : Fragment() {
    private lateinit var clientiViewModel: ClientiViewModel
    private val navigationArgs: ClientDetaliiFragmentArgs by navArgs()
    lateinit var client: Client


    private var googleMap: GoogleMap? = null

    private var _binding: FragmentClientDetaliiBinding? = null;
    private val binding get() = _binding!!

    private lateinit var appDatabase: AppDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ):
            View? {
        clientiViewModel = ViewModelProvider(this).get(ClientiViewModel::class.java)
        _binding = FragmentClientDetaliiBinding.inflate(inflater, container, false)
        val root: View = binding.root

        clientiViewModel.initDatabase(requireContext())

        binding.mapView.onCreate(savedInstanceState)
        binding.mapView.getMapAsync { map ->
            googleMap = map
        }



        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = navigationArgs.clientId
        clientiViewModel.retrieveItem(id).observe(this.viewLifecycleOwner) { selectedClient ->
            client = selectedClient
            completareTV(client)
        }

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
            if (!client.platitorDeTVA!!) {
                checkBoxTVA.text = "Nu este platitor de tva"
            }


            var adresaCompleta = "${client.localitate} ${client.adresa}"
           // adresaCompleta= "Bucuresti Strada Crinul de Padure"
            val geocoder = Geocoder(requireContext())
            val addressList = geocoder.getFromLocationName(adresaCompleta, 1)
            if (addressList != null && addressList.isNotEmpty()) {
                val location = addressList[0]
                val latLng = LatLng(location.latitude, location.longitude)
                // ad marker
                googleMap?.addMarker(MarkerOptions().position(latLng).title("Client Location"))
                // Centrare harta + lvl de zoom
                googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f))
            }


            btnStergereClient.setOnClickListener { showConfirmationDialog() }
            fabEditClient.setOnClickListener {
                val action =
                    ClientDetaliiFragmentDirections.actionClientDetaliiFragment2ToAdaugaClientFragment(
                        "Editare client",
                        client.idClient
                    )
                findNavController().navigate(action)
            }
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


    override fun onResume() {
        super.onResume()
        binding.mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView?.onPause()
    }
    override fun onDestroy() {
        super.onDestroy()
        binding.mapView?.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding.mapView?.onSaveInstanceState(outState)
    }
}


