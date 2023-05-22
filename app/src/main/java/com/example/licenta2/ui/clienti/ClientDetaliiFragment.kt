package com.example.licenta2.ui.clienti

import android.content.Context
import android.graphics.BitmapFactory
import android.location.Geocoder
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.licenta2.MySharedPreferences
import com.example.licenta2.databinding.FragmentClientDetaliiBinding
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Client
import com.example.licenta2.ui.clienti.adaugare_client.WeatherApiClient
import com.example.licenta2.ui.clienti.adaugare_client.WeatherResponse
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException
import java.util.*
import android.location.Address;
import android.net.Uri
import androidx.core.net.toUri
import com.bumptech.glide.Glide

import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place.Field
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FetchPlaceResponse
import com.google.android.libraries.places.api.net.PlacesClient


class ClientDetaliiFragment : Fragment() {
    private lateinit var clientiViewModel: ClientiViewModel
    private val navigationArgs: ClientDetaliiFragmentArgs by navArgs()
    lateinit var client: Client
    private lateinit var mySharedPreferences: MySharedPreferences

    private var googleMap: GoogleMap? = null
    private lateinit var selectedMapModeShared: String

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
        mySharedPreferences = MySharedPreferences(requireContext())


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
            val imagePath = client.imagePath
            val uri: Uri = Uri.parse(imagePath)

            Glide.with(requireContext())
                .load(uri)
                .into(imgvDetaliiClient)

//map si vreme
            val adresaClient = "${client.localitate} ${client.adresa}"


            try {


                if (!adresaClient.isEmpty()) {
                    val latLng = getLocationFromAddress(requireContext(), adresaClient)
                    // ad marker
                    googleMap?.addMarker(MarkerOptions().position(latLng!!).title(client.denumire))
                    // Centrare harta + lvl de zoom
                    val nivelZoom = mySharedPreferences.getMapZoom()
                    googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng!!, nivelZoom))
                    googleMap?.mapType = mySharedPreferences.getMapMode()

                    if (latLng != null) {
                        getWeatherDetails(latLng.latitude, latLng.longitude)
                    }
                }
            } catch (e: IOException) {
                val latitudinePredefinita = 44.421992
                val longitudinePredefinita = 26.031998
                Toast.makeText(requireContext(), "Eroare la geocodare", Toast.LENGTH_SHORT).show()

                val latLng = LatLng(latitudinePredefinita, longitudinePredefinita)
                googleMap?.addMarker(MarkerOptions().position(latLng).title(client.denumire))
                val nivelZoom = mySharedPreferences.getMapZoom()
                googleMap?.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, nivelZoom))
                googleMap?.mapType = mySharedPreferences.getMapMode()
                getWeatherDetails(latitudinePredefinita, longitudinePredefinita)
            }





            //btn stergere
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



    fun getLocationFromAddress(context: Context, strAddress: String): LatLng? {
        val coder = Geocoder(context)
        var addressList: List<Address>? = null
        var p1: LatLng? = null

        try {
            // May throw an IOException
            addressList = coder.getFromLocationName(strAddress, 5)
            if (addressList == null) {
                p1 = LatLng(44.42199226, 26.031998)
                return p1
            }

            val location = addressList[0]
            p1 = LatLng(location.latitude, location.longitude)

        } catch (ex: IOException) {
            p1 = LatLng(44.42199226, 26.031998)
            return p1
        }

        return p1
    }

    private fun getWeatherDetails(latitude: Double, longitude: Double) {
        val apiKey = "7706df1a3d4cf2246208c814647b627a"

        val call = WeatherApiClient.weatherApiService.getWeatherData(latitude, longitude, apiKey)
        call.enqueue(object : Callback<WeatherResponse> {

            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    val weatherResponse = response.body()

                    if (weatherResponse != null) {
                        val main = weatherResponse.weather[0].main
                        val description = weatherResponse.weather[0].description
                        val temp = String.format("%.2f", weatherResponse.main.temp - 273.15) + "°C"
                        val pressure = weatherResponse.main.pressure.toString() + " hPa"
                        val humidity = weatherResponse.main.humidity.toString() + "%"

                        val detaliiVreme = "Detalii vreme:\n" +
                                "In principal: $main\n" +
                                "Descriere: $description\n" +
                                "      Temperatura: $temp\n" +
                                "      Presiune: $pressure\n" +
                                "      Umiditate: $humidity"

                        binding.tvVreme.text = detaliiVreme
                    }

                } else {
                    Toast.makeText(
                        requireContext(),
                        "Fetch pentru datele de vreme nu a functionat",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Toast.makeText(requireContext(), "Eroare: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }


    private fun showConfirmationDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage("Dorești să ștergi acest client?")
            .setCancelable(false) //daca apesi in exterior, nu se inchide
            .setNegativeButton("Nu") { dialog, _ ->
                dialog.dismiss() // inchid caseta
            }
            .setPositiveButton("Da") { dialog, _ ->
                deleteClient()
                dialog.dismiss()
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


