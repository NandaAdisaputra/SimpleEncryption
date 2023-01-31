package com.nandaadisaputra.simpleencryption.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.nandaadisaputra.simpleencryption.R
import com.nandaadisaputra.simpleencryption.data.Session
import com.nandaadisaputra.simpleencryption.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//Karena Kita pakai DI Hilt jangan lupa tambahkan anotasi
//@AndroidEntryPoint pada setiap class Activity
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    //Karena Kita sudah pakai DI Hilt maka Kita dapat memanggil session
    //dengan cara @Inject
    @Inject
    lateinit var session: Session

    /*Kita deklarasikan juga binding yang akan Kita gunakan*/
    private val binding: ActivityMainBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_main)
    }

    /*Karena Kita menerapkan DataBinding*/
    var usernameUser = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*inisialisasi dulu activitynya*/
        /*wajib karena Kita memakai DataBinding*/
        binding.activity = this
        binding.lifecycleOwner = this
        /*Kita akan memakai fungsi  checkData()
        * * maka perlu Kita deklarasikan dulu */
        checkData()
    }

    private fun checkData() {
        //Periksa apakah sudah ada session yang masih tersimpan atau belum
        if (session.getIsSession()) {
            //Jika sudah ada session yang masih tersimpan maka
            //akan langsung masuk halaman HomeActivity.kt
            //Jika belum ada session maka akan masuk halaman ini
            //MainActivity.kt
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    fun saveUsername() {
        /*Simpan Nama User ketika klik button Save */
        session.savePrefString(session.yourUsername, usernameUser)
        /*Simpan pernyataan bahwa sudah menyimpan session username*/
        session.savePrefBoolean(session.isSession, true)
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}