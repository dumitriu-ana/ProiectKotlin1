package com.example.licenta2.persistence.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "clienti")
data class Client(
    @PrimaryKey(autoGenerate = true) val idClient: Int = 0,
    @ColumnInfo(name = "cif") val cif: String?,
    @ColumnInfo(name = "denumire") val denumire: String?,
    @ColumnInfo(name = "regcom") val regCom: String?,
    @ColumnInfo(name = "platitorDeTVA") val platitorDeTVA: Boolean?,

    @ColumnInfo(name = "localitate") val localitate: String?,
    @ColumnInfo(name = "judet") val judet: String?,
    @ColumnInfo(name = "adresa") val adresa: String?,

    @ColumnInfo(name = "nume") val nume: String?,
    @ColumnInfo(name = "telefon") val telefon: String?,
    @ColumnInfo(name = "email") val email: String?,
    val imagePath:String?
)


