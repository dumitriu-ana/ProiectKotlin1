package com.example.licenta2.ui.facturi.adaugare_facturi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.licenta2.R
import com.example.licenta2.persistence.entities.Factura
import com.example.licenta2.persistence.entities.Produs
import java.text.NumberFormat
import java.util.*


class ListaFinalaProdAdaptor(private val context: Context, private var produse: List<Produs>) : RecyclerView.Adapter<ListaFinalaProdAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.lv_row_lista_finala_produse, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produs = produse[position]

        holder.produsDenumire.text = produs.denumire
        val pretFormatat = NumberFormat.getNumberInstance(Locale.US).apply {
            maximumFractionDigits = 2
        }.format(produs.pret)
        holder.produsPret.text = pretFormatat.toString()
       // holder.produsCantitate.text = "1"


    }

    override fun getItemCount(): Int {
        return produse.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
       val produsDenumire:TextView = itemView.findViewById(R.id.denumireTextView)
        val produsPret:TextView = itemView.findViewById(R.id.pretTextView)
        val produsCantitate:EditText = itemView.findViewById(R.id.cantitateEditText)

    }
}

