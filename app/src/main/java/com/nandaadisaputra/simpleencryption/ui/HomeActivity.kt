package com.nandaadisaputra.simpleencryption.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.nandaadisaputra.simpleencryption.R
import com.nandaadisaputra.simpleencryption.data.Session
import com.nandaadisaputra.simpleencryption.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

//Karena Kita pakai DI Hilt jangan lupa tambahkan anotasi
//@AndroidEntryPoint pada setiap class Activity
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    //Karena Kita sudah pakai DI Hilt maka Kita dapat memanggil session
    //dengan cara @Inject
    @Inject
    lateinit var session: Session

    /*Kita deklarasikan juga binding yang akan Kita gunakan*/
    private val binding: ActivityHomeBinding by lazy {
        DataBindingUtil.setContentView(this, R.layout.activity_home)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*inisialisasi dulu activitynya*/
        /*wajib karena Kita memakai DataBinding*/
        binding.activity = this
        binding.lifecycleOwner = this

        // //mendapatkan username dari session
        // kemudian disampungkan dengan layout
        binding.tvHallo.text = "Hallo ${session.getUsername()}"
    }

    fun logOut() {
        //Ketika button logout ditekan maka simpan pernyataan bahwa session terhapus
        session.savePrefBoolean(session.isSession,false)
        //Setelah session terhapus maka akan masuk ke MainActivity.kt
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}