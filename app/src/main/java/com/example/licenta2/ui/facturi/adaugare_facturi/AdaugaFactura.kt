package com.example.licenta2.ui.facturi.adaugare_facturi

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.licenta2.R
import com.example.licenta2.databinding.FragmentAdaugaFacturaBinding
import com.example.licenta2.persistence.database.AppDatabase
import com.example.licenta2.persistence.entities.Client
import com.example.licenta2.persistence.entities.Factura
import com.example.licenta2.ui.facturi.adaugare_facturi.selectie.SharedViewModel
import com.example.licenta2.ui.showSnackbar
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

fun currentDate(): String {
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    return LocalDate.now().format(dateFormatter)
}

fun adaugareZile(dateString: String, days: Int): String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val date = LocalDate.parse(dateString, formatter)
    val newDate = date.plusDays(days.toLong())
    return newDate.format(formatter)
}

fun checkData(data: String, dataComparare: String): Boolean {
    return data.isNotBlank() && data > dataComparare

}

class AdaugaFactura : Fragment(), CellClickListener {
    private lateinit var adaugaFacturaViewModel: AdaugaFacturaViewModel
    private var _binding: FragmentAdaugaFacturaBinding? = null;
    private val binding get() = _binding!!

    private lateinit var appDatabase: AppDatabase
    private lateinit var recyclerView: RecyclerView
    private lateinit var listaProduseFinaleAdaptor: ListaFinalaProdAdaptor

    var client: Client? = null

    //shared vm
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        adaugaFacturaViewModel = ViewModelProvider(this).get(AdaugaFacturaViewModel::class.java)
        _binding = FragmentAdaugaFacturaBinding.inflate(inflater, container, false)
        val root: View = binding.root

        adaugaFacturaViewModel.initDatabase(requireContext())
        appDatabase = AppDatabase.getDatabase(requireContext())


        binding.facturaAdaugaClient.setOnClickListener {
            findNavController().navigate(R.id.action_adaugaFactura_to_selectareClientFragment)
        }

        sharedViewModel.selectedClient.observe(viewLifecycleOwner, Observer<Client> {
            Toast.makeText(requireContext(), "in obs  " +sharedViewModel.selectedClient.value, Toast.LENGTH_LONG).show()
            binding.tvAdaugareFacturaClient.text = it.denumire

            val stringAdresa = "${it.judet}, ${it.localitate}, ${it.adresa}"
            binding.editTextAdresaFactura.hint = stringAdresa
        })


        binding.buttonAdaugaProdusFactura.setOnClickListener {
            findNavController().navigate(R.id.action_adaugaFactura_to_selectareProdusFragment)
        }


        recyclerView = binding.rvFacturaListaProduse
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        listaProduseFinaleAdaptor = ListaFinalaProdAdaptor(requireContext(), emptyList())
        recyclerView.adapter = listaProduseFinaleAdaptor

        sharedViewModel.listaFinalaProduse.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Lista p  $it", Toast.LENGTH_LONG).show()

            listaProduseFinaleAdaptor = ListaFinalaProdAdaptor(requireContext(), it)
            recyclerView.adapter = listaProduseFinaleAdaptor
        }



        val dateEditText = root.findViewById<EditText>(R.id.editTextData)
        val dataScadentaEditText = root.findViewById<EditText>(R.id.editTextDataScadenta)
        val dataLivrariiEditText = root.findViewById<EditText>(R.id.editTextDataLivrarii)
        val dataIncasariiEditText = root.findViewById<EditText>(R.id.editTextDataIncasarii)




        dataLivrariiEditText.text = Editable.Factory.getInstance().newEditable(
            adaugareZile(
                currentDate(), 3
            )
        )
        dataLivrariiEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            // Creeaza si afiseaza DatePickerDialog
            val datePickerDialog =
                DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
                    val selectedDate = "${dayOfMonth}/${month + 1}/${year}"
                    dataLivrariiEditText.setText(selectedDate)
                }, year, month, day)
            datePickerDialog.show()
        }


        dataScadentaEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog =
                DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
                    val selectedDate = "${dayOfMonth}/${month + 1}/${year}"
                    dataScadentaEditText.setText(selectedDate)
                }, year, month, day)
            datePickerDialog.show()
        }
        dateEditText
            .text = Editable.Factory.getInstance().newEditable(currentDate())
        dateEditText.setOnClickListener {
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)
            val datePickerDialog =
                DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
                    val selectedDate = "${dayOfMonth}/${month + 1}/${year}"
                    dateEditText.setText(selectedDate)
                }, year, month, day)
            datePickerDialog.show()
        }


        dataIncasariiEditText.text = Editable.Factory.getInstance().newEditable(
            adaugareZile(
                currentDate(), 3
            )
        )
        dataIncasariiEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog =
                DatePickerDialog(requireContext(), { _, year, month, dayOfMonth ->
                    val selectedDate = "${dayOfMonth}/${month + 1}/${year}"
                    dataIncasariiEditText.setText(selectedDate)
                }, year, month, day)
            datePickerDialog.show()
        }

        val spinnerTermenDePlata = root.findViewById<Spinner>(R.id.spinnerTermenDePlata)
        val adapterTermenDePlata = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.spinnerTermenDePlata_items,
            android.R.layout.simple_spinner_item
        )
        adapterTermenDePlata.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTermenDePlata.adapter = adapterTermenDePlata

        spinnerTermenDePlata.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent.getItemAtPosition(position).toString()
                val termen = selectedItem.toInt()
                dataScadentaEditText.text = Editable.Factory.getInstance().newEditable(
                    adaugareZile(currentDate(), termen)


                )
                //Toast.makeText(this@AdaugaProdus, "Selected: $selectedItem", Toast.LENGTH_SHORT).show()
            }


            override fun onNothingSelected(parent: AdapterView<*>) {}
        }




        binding.butonSalvareFactura.setOnClickListener {
            writeData()
        }

        return root
    }

    private fun writeData() {
        var valid = true

        val client = binding.tvAdaugareFacturaClient
        if(client.text.toString().isBlank()){
            client.error = "Selecteaza client"
            valid = false
        }

        val adresaLivrare = binding.editTextAdresaFactura
        if (adresaLivrare.text.toString().isBlank()) {
            adresaLivrare.error = "Adauga adresa"
            valid = false
        }
        val data = binding.editTextData
        if (data.text.toString().isBlank()) {
            data.error = "Adauga data"
            valid = false
        }
        val serie = binding.editTextSerieFactura
        //seria este formata dintr-un sir scurt de caractere si un numar unic, ultimul preluat din BD+1
        if (serie.text.toString().isBlank()) {
            serie.error = "Adauga seria"
            valid = false
        }
        //lista prod
        //
        val termenDePlata = binding.spinnerTermenDePlata.selectedItem.toString()

        val dataScadenta = binding.editTextDataScadenta
        if (!checkData(dataScadenta.text.toString(), data.text.toString())) {
            dataScadenta.error = "Introdu o data scadenta valida"
            valid = false
        }

        val dataLivrarii = binding.editTextDataLivrarii
        if (!checkData(dataLivrarii.text.toString(), data.text.toString())) {
            dataLivrarii.error = "Introdu o data de livrare valida"
            valid = false
        }
        //val dataIncasarii = binding.editTextDataIncasarii.text.toString()
        val campDataIncasarii = binding.editTextDataIncasarii
        if (!checkData(campDataIncasarii.text.toString(), data.text.toString())) {
            campDataIncasarii.error = "Introdu o data a incasarii valida"
            valid = false
        }
        val intocmitDe = binding.editTextIntocmitDe.text.toString()
        val mentiuni = binding.editTextMentiuni.text.toString()

        if (valid) {

            val factura = Factura(
                client = client.text.toString(),
                adresaLivrare = adresaLivrare.text.toString(),
                data = data.text.toString(),
                serie = serie.text.toString(),
                //prod
                termenDePlata = termenDePlata,
                dataScadenta = dataScadenta.text.toString(),
                dataLivrarii = dataLivrarii.text.toString(),
                dataIncasarii = campDataIncasarii.text.toString(),
                intocmitDe = intocmitDe,
                mentiuni = mentiuni
            )
            adaugaFacturaViewModel.insertFactura(factura)
            // Toast.makeText(requireContext(), "Fact", Toast.LENGTH_SHORT).show()
            view?.let {
                showSnackbar(requireContext(), it, "Factura introdusa cu succes!", false)
            }
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onCellClickListener(data: Client) {
        //Toast.makeText(requireContext(), "apasat" + data.denumire, Toast.LENGTH_SHORT).show()
       // binding.tvAdaugareFacturaClient.text = data.denumire
    }
}