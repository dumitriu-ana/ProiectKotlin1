package com.example.licenta2.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.licenta2.persistence.entities.Client

@Dao
interface ClientDao {
    @Query("SELECT * FROM clienti")
    fun getAllClienti(): List<Client>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertClient(client: Client)

}
