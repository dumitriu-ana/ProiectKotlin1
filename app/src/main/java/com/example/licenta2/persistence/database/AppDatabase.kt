package com.example.licenta2.persistence.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.licenta2.persistence.dao.*
import com.example.licenta2.persistence.entities.*
import com.example.licenta2.ui.produse.adaugare_produse.AdaugaProdus


@Database(entities = [Produs::class, Client::class, Factura::class, Incasare::class, FacturaProdusCross::class], version = 12)
abstract class AppDatabase : RoomDatabase() {

    abstract fun clientDao():ClientDao
    abstract fun produsDao(): ProdusDao
    abstract fun facturaDao():FacturaDao
    abstract fun incasareDao():IncasareDao
    abstract fun facturaClientCrossDao(): FacturaProdusCrossDao

    companion object {
        @Volatile
            private var INSTANCE: AppDatabase? = null


        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
