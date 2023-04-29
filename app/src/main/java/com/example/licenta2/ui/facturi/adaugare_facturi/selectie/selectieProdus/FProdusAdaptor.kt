package com.example.licenta2.ui.facturi.adaugare_facturi.selectie.selectieProdus

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.licenta2.R
import com.example.licenta2.persistence.entities.Produs
import com.example.licenta2.ui.facturi.adaugare_facturi.ProdusClickListener


class FProdusAdaptor(private val context: Context, private var produse: List<Produs>,
                     private val produsClickListener: ProdusClickListener) :
    RecyclerView.Adapter<FProdusAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.lv_row_factura_produs, parent, false)
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produs = produse[position]
        holder.prodNameTextView.text = produs.denumire
        holder.prodPret.text = produs.pret.toString()

        holder.itemView.setOnClickListener {
            it.setBackgroundColor(context.getColor(R.color.btn_orange))
            produsClickListener.onProdusClickListener(produs)
        }
    }

    override fun getItemCount(): Int {
        return produse.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val prodNameTextView: TextView = itemView.findViewById(R.id.tv_denumire_produs)
        val prodPret: TextView = itemView.findViewById(R.id.tv_pret_produs)

    }
}