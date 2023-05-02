package com.example.licenta2.ui.facturi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.licenta2.R
import com.example.licenta2.persistence.entities.Factura

class FacturaAdaptor(private val context: Context, private var facturi: List<Factura>) : RecyclerView.Adapter<FacturaAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.lv_row_factura, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val factura = facturi[position]

        holder.facturaClientTextView.text = factura.client
        holder.facturaSerieTextView.text = factura.serie
        holder.facturaDataTextView.text = factura.data.toString()
        holder.facturaDataScadentaTextView.text = factura.dataScadenta.toString()
        holder.facturaDataIncasariiTextView.text = factura.dataIncasarii.toString()
       // holder.facturaStatusTextView.text = "hopa"
        holder.facturaSumaTextView.text = "0.0"
    }

    override fun getItemCount(): Int {
        return facturi.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val facturaClientTextView: TextView = itemView.findViewById(R.id.factura_client)
        val facturaSerieTextView: TextView = itemView.findViewById(R.id.factura_serie)
        val facturaDataTextView: TextView = itemView.findViewById(R.id.factura_data)
        val facturaDataScadentaTextView: TextView = itemView.findViewById(R.id.factura_data_scadenta)
        val facturaDataIncasariiTextView: TextView = itemView.findViewById(R.id.factura_data_incasarii)
       // val facturaStatusTextView: TextView = itemView.findViewById(R.id.factura_status)
        val facturaSumaTextView: TextView = itemView.findViewById(R.id.factura_suma)
    }
}
