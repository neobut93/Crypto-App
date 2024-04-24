package com.cryptoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.cryptoapp.models.Crypto

private const val DATABASE_NAME = "crypto_database"
private const val DATABASE_VERSION = 1

@Database(entities = [Crypto::class], version = DATABASE_VERSION, exportSchema = false)
abstract class CryptoDatabase : RoomDatabase() {

    abstract fun cryptoDao(): CryptoDao

    companion object {
        fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            CryptoDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
}