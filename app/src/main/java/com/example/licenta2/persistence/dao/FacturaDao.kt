package com.example.licenta2.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.licenta2.persistence.entities.Factura
import com.example.licenta2.persistence.entities.Produs

@Dao
interface FacturaDao {
    @Query("SELECT * FROM facturi order by data desc")
    fun getAllFacturi(): LiveData<List<Factura>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFactura(factura: Factura)

    @Query("DELETE from facturi")
    fun stergereToateFacturile()
}
