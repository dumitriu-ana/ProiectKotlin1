package com.example.licenta2.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.licenta2.persistence.entities.Produs

@Dao
interface ProdusDao {
    @Query("SELECT * FROM produse order by denumire")
    fun getAllProduse(): LiveData<List<Produs>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProdus(produs: Produs)

    @Query("DELETE from produse")
    fun stergereToateProdusele()
}
