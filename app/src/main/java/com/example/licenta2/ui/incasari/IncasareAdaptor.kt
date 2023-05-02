package com.example.licenta2.ui.incasari

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.licenta2.R
import com.example.licenta2.persistence.entities.Incasare

class IncasareAdaptor(private val context: Context, private var incasari: List<Incasare>) : RecyclerView.Adapter<IncasareAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.lv_row_incasare, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val incasare = incasari[position]

        holder.incasareClientTextView.text = incasare.clientIncasare
        holder.incasareNumarTextView.text = incasare.numarIncasare.toString()
        holder.incasareDataTextView.text = incasare.dataIncasare.toString()
        holder.incasareTipTextView.text = incasare.tipDocument.toString()
        holder.incasareSumaTextView.text = incasare.valoare.toString()
    }

    override fun getItemCount(): Int {
        return incasari.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val incasareClientTextView: TextView = itemView.findViewById(R.id.incasare_client)
        val incasareNumarTextView: TextView = itemView.findViewById(R.id.incasare_numar)
        val incasareDataTextView: TextView = itemView.findViewById(R.id.incasare_data)
        val incasareTipTextView: TextView = itemView.findViewById(R.id.incasare_tip)

        val incasareSumaTextView: TextView = itemView.findViewById(R.id.incasare_suma)
    }
}
