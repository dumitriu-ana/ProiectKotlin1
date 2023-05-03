package com.example.licenta2.persistence.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.versionedparcelable.VersionedParcelize


@Entity(tableName = "produse")
data class Produs(
    @PrimaryKey(autoGenerate = true) val idProdus: Int = 0,
    @ColumnInfo(name = "denumire") val denumire: String?,
    @ColumnInfo(name = "unitateDeMasura") val unitateDeMasura: String?,
    @ColumnInfo(name = "pret") val pret: Double?,
    @ColumnInfo(name = "cotaTVA") val cotaTVA: Double?,
    @ColumnInfo(name = "contineTVA") val contineTVA: Boolean?
)


