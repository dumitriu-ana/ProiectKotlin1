package com.example.licenta2.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.licenta2.persistence.entities.Factura
import com.example.licenta2.persistence.entities.Incasare
import com.example.licenta2.persistence.entities.Produs

@Dao
interface IncasareDao {
    @Query("SELECT * FROM incasari order by dataIncasare desc")
    fun getAllIncasari(): LiveData<List<Incasare>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIncasare(incasare: Incasare)

}
