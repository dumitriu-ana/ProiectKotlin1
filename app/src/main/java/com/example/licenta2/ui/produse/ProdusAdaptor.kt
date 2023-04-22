package com.example.licenta2.ui.produse

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.licenta2.R
import com.example.licenta2.persistence.entities.Produs

class ProdusAdaptor(private val context: Context, private var produse: List<Produs>) : RecyclerView.Adapter<ProdusAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.lv_row_produs, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produs = produse[position]

        holder.prodNameTextView.text = produs.denumire
        holder.prodPretCuTvaTextView.text = produs.pret.toString()
        holder.prodPretFaraTextView.text = produs.pret.toString()
        holder.prodCotaTvaTextView.text = produs.cotaTVA.toString()
        holder.prodUmTextView.text = produs.unitateDeMasura.toString()
    }

    override fun getItemCount(): Int {
        return produse.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val prodNameTextView: TextView = itemView.findViewById(R.id.produs_nume)
        val prodPretCuTvaTextView: TextView = itemView.findViewById(R.id.produs_cota_tva)
        val prodPretFaraTextView: TextView = itemView.findViewById(R.id.produs_pret_fara_tva)
        val prodCotaTvaTextView: TextView = itemView.findViewById(R.id.produs_cota_tva)
        val prodUmTextView: TextView = itemView.findViewById(R.id.produs_um)
    }
}





