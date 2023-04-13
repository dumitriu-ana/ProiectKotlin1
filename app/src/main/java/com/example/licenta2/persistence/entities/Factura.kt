package com.example.licenta2.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "facturi")
data class Factura(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name="client") val client:String?,
    @ColumnInfo(name = "adresaLivrare") val adresaLivrare: String?,
    @ColumnInfo(name = "data") val data: String,
    @ColumnInfo(name = "serie") val serie: String?,
    //coloana cu lista produse
   // @ColumnInfo(name = "listaProduse") val produse: List<Produs>?,
    @ColumnInfo(name = "termenDePlata") val termenDePlata: String?,
    @ColumnInfo(name = "dataScadenta") val dataScadenta: String,
    @ColumnInfo(name = "dataLivrarii") val dataLivrarii: String,
    @ColumnInfo(name = "DataIncasarii") val dataIncasarii: String,
    @ColumnInfo(name = "intocmitDe") val intocmitDe: String?,
    @ColumnInfo(name = "mentiuni") val mentiuni: String?,
)


