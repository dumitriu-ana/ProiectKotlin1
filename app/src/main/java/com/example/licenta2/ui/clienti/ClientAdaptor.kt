package com.example.licenta2.ui.clienti

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.licenta2.R
import com.example.licenta2.persistence.entities.Client

class ClientAdaptor(private val context: Context, private var clients: List<Client>) :
    RecyclerView.Adapter<ClientAdaptor.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.lv_row_clienti, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val client = clients[position]

        holder.clientDenumireTextView.text = client.denumire
        holder.clientCifTextView.text = client.cif
        holder.clientNumeTextView.text = client.nume
        holder.clientMailTextView.text = client.email
        holder.clientTelefonTextView.text = client.telefon
        holder.clientAdresaTextView.text = client.adresa
        holder.clientRegistrulComertuluiTextView.text = client.regCom
    }

    override fun getItemCount(): Int {
        return clients.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val clientDenumireTextView: TextView = itemView.findViewById(R.id.client_denumire)
        val clientCifTextView: TextView = itemView.findViewById(R.id.client_cif)
        val clientNumeTextView: TextView = itemView.findViewById(R.id.client_nume)
        val clientMailTextView: TextView = itemView.findViewById(R.id.client_mail)
        val clientTelefonTextView: TextView = itemView.findViewById(R.id.client_telefon)
        val clientAdresaTextView: TextView = itemView.findViewById(R.id.client_adresa)
        val clientRegistrulComertuluiTextView: TextView = itemView.findViewById(R.id.client_registrul_comertului)
    }
}
