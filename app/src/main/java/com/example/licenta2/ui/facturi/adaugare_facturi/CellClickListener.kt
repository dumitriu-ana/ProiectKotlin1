package com.example.licenta2.ui.facturi.adaugare_facturi

import com.example.licenta2.persistence.entities.Client
import com.example.licenta2.persistence.entities.Produs

interface CellClickListener {
        fun onCellClickListener(data: Client)

}
interface ProdusClickListener{
    fun onProdusClickListener(data: Produs)
}