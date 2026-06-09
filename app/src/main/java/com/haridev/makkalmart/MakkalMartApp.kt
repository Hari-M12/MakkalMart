package com.haridev.makkalmart

import android.app.Application
import androidx.room.Room
import com.haridev.makkalmart.data.db.MakkalMartDatabase

class MakkalMartApp: Application(){
    lateinit var database: MakkalMartDatabase

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(applicationContext,
            MakkalMartDatabase::class.java,"makkalmart_db").build()
    }
}