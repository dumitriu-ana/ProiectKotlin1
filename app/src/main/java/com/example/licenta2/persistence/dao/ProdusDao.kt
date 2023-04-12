package com.example.licenta2.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.licenta2.persistence.entities.Produs

@Dao
interface ProdusDao {
    @Query("SELECT * FROM produse")
    fun getAllProduse(): List<Produs>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProdus(produs: Produs)

}
