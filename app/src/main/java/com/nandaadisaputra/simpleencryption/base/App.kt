package com.nandaadisaputra.simpleencryption.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Karena Kita menggunakan DI Hilt maka jangan lupa
//Tambahkan anotasi @HiltAndroidApp
@HiltAndroidApp
class App : Application()