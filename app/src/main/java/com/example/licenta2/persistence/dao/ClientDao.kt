package com.example.licenta2.persistence.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.licenta2.persistence.entities.Client
import kotlinx.coroutines.flow.Flow

@Dao
interface ClientDao {
    @Query("SELECT * FROM clienti order by denumire")
    fun getAllClienti(): LiveData<List<Client>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertClient(client: Client)

    @Query("DELETE from clienti")
    fun stergereTotiClientii()

    @Query("SELECT * from clienti WHERE idClient = :id")
    fun getItem(id: Int): Flow<Client>

    @Update
    suspend fun update(client: Client)

    @Delete
    suspend fun delete(client: Client)

}
