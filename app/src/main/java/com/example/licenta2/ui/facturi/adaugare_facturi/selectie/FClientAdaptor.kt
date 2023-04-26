package com.example.licenta2.ui.facturi.adaugare_facturi.selectie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.licenta2.R
import com.example.licenta2.persistence.entities.Client
import com.example.licenta2.ui.clienti.ClientAdaptor

class FClientAdaptor (private val context: Context, private var clients: List<Client>) :
RecyclerView.Adapter<FClientAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FClientAdaptor.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.lv_row_factura_client, parent, false)
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: FClientAdaptor.ViewHolder, position: Int) {
        val client = clients[position]
        holder.clientDenumireTextView.text = client.denumire
        holder.clientCUITextView.text=client.regCom
    }

    override fun getItemCount(): Int {
        return clients.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val clientDenumireTextView: TextView = itemView.findViewById(R.id.tv_denumire_client)
        val clientCUITextView: TextView = itemView.findViewById(R.id.tv_CUI_client)
    }
}

