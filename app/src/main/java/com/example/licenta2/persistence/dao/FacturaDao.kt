package com.example.licenta2.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.licenta2.persistence.entities.Factura
import com.example.licenta2.persistence.entities.FacturaCuProduse
import com.example.licenta2.persistence.entities.FacturaProdusCross
import com.example.licenta2.persistence.entities.Produs

@Dao
interface FacturaDao {

    @Query("SELECT * FROM facturi order by data desc")
    fun getAllFacturi(): LiveData<List<Factura>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFactura(factura: Factura)



    @Query("DELETE from facturi")
    fun stergereToateFacturile()

    @Query("SELECT MAX(idFactura) FROM facturi")
    fun getMaxFacturaId(): Int


}
