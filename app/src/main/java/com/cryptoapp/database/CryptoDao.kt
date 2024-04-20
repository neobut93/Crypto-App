package com.kodeco.android.countryinfo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.cryptoapp.models.Crypto

@Dao
interface CryptoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCrypto(crypto: Crypto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCryptos(cryptos: List<Crypto>)

    @Query("SELECT * FROM cryptos")
    suspend fun getAllCryptos() : List<Crypto>

    @Query("SELECT * FROM cryptos WHERE id = :id")
    suspend fun getCrypto(id: String): Crypto

    @Query("DELETE FROM cryptos")
    suspend fun deleteAllCryptos()

    @Update
    suspend fun updateCrypto(crypto: Crypto)
}