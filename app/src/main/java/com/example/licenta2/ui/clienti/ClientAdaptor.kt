package com.example.licenta2.ui.clienti

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.licenta2.R
import com.example.licenta2.persistence.entities.Client

class ClientAdaptor(
    private val context: Context,
    var clients: List<Client>
) : RecyclerView.Adapter<ClientAdaptor.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(client: Int)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.lv_row_clienti, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val client = clients[position]

        val adresaTotal = "${client.judet}, ${client.localitate}, ${client.adresa}"
        holder.clientDenumireTextView.text = client.denumire
        holder.clientCifTextView.text = client.cif
        holder.clientNumeTextView.text = client.nume
        holder.clientMailTextView.text = client.email
        holder.clientTelefonTextView.text = client.telefon
        holder.clientAdresaTextView.text = adresaTotal
        holder.clientRegistrulComertuluiTextView.text = client.regCom

//        Glide.with(context)
//            .load(Uri.parse(client.imagePath))
//            .into(holder.clientImagine)

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(client.idClient)
        }
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
        val clientRegistrulComertuluiTextView: TextView =
            itemView.findViewById(R.id.client_registrul_comertului)
        val clientImagine: ImageView = itemView.findViewById(R.id.imgv_client_adapter)
    }
}
