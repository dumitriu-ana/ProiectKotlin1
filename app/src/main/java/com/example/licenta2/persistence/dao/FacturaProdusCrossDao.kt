package com.example.licenta2.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.licenta2.persistence.entities.Factura
import com.example.licenta2.persistence.entities.FacturaProdusCross
@Dao
interface FacturaProdusCrossDao {

    @Query("SELECT * FROM FacturaProdusCross")
    fun getAllFacturiProduseDao(): LiveData<List<FacturaProdusCross>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFacturaProdusCross(facturaProdusCross: FacturaProdusCross)


}