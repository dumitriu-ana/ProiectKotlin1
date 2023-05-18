package com.example.licenta2.persistence.entities

import androidx.room.*

@Entity(tableName = "FacturaProdusCross",
    primaryKeys = ["idFactura", "idProdus"])
data class FacturaProdusCross(
    @ColumnInfo(name="idFactura") val idFactura: Int,
    @ColumnInfo(name="idProdus") val idProdus: Int,
    @ColumnInfo(name = "pretFacturare") val pretFacturare: Double?,
    @ColumnInfo(name="cantitate") val cantitate: Double?
)

data class FacturaCuProduse(

    @Embedded val factura: Factura,
    @Relation(
        parentColumn = "idFactura",
        entityColumn = "idProdus",
        associateBy = Junction(FacturaProdusCross::class)
    )
    val produseLista: List<Produs>
)

data class ProduseInFacturi(
    @Embedded val produs: Produs,
    @Relation(
        parentColumn = "idProdus",
        entityColumn = "idFactura",
        associateBy = Junction(FacturaProdusCross::class)
    )
    val facturiLista: List<Factura>
)