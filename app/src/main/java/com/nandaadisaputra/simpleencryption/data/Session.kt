package com.nandaadisaputra.simpleencryption.data

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.securepreferences.SecurePreferences

class Session(context: Context) {
    val yourUsername = "Username"
    val isSession = "Session"

    private var pref: SharedPreferences = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val spec = KeyGenParameterSpec.Builder(
            MasterKey.DEFAULT_MASTER_KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
            .setKeySize(MasterKey.DEFAULT_AES_GCM_MASTER_KEY_SIZE)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
            .build()
        val masterKey = MasterKey.Builder(context)
            .setKeyGenParameterSpec(spec)
            .build()
        //EncryptionSharedPreference adalah nama file shared preference.
        EncryptedSharedPreferences
            .create(
                context,
                "SharedPreference",
                masterKey,
                //algoritma AES GCM 256
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
    } else {
        SecurePreferences(context)
    }
    private var editor: SharedPreferences.Editor = pref.edit()

    //Untuk Menyimpan session bertipe String
    fun savePrefString(key: String, value: String) {
        editor.putString(key, value)
        editor.commit()
    }

    //Untuk Menyimpan Pernyataan sudah ada session atau belum
    fun savePrefBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value)
        editor.commit()
    }

    //Untuk mengambil session Username
    fun getUsername(): String? {
        return pref.getString(yourUsername, "")
    }

    //Untuk Mengecek apakah ada session atau tidak
    fun getIsSession(): Boolean {
        return pref.getBoolean(isSession, false)
    }
}
