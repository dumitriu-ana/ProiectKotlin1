package com.example.licenta2.persistence.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "incasari")
data class Incasare(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name="numarIncasare") val numarIncasare:Int?,
    @ColumnInfo(name = "clientIncasare") val clientIncasare: String?,
    @ColumnInfo(name = "tipDocument") val tipDocument: String,
    @ColumnInfo(name = "valoare") val valoare: Double?,
    @ColumnInfo(name = "dataIncasare") val dataIncasare: String?,
    @ColumnInfo(name = "reprezentand") val reprezentand: String
)


