package com.example.myapplication.room

import android.content.Context
import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.myapplication.common.Converter

@Database(entities = [Contact::class],version = 1)
@TypeConverters(Converter::class)
abstract class ContactDatabase :RoomDatabase(){

    abstract fun contactDao():ContactDAO

    companion object{

        val migrate_1_2= object : Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Contact ADD COLUMN isActive INTEGER NOT NULL DEFAULT(1)")
            }

        }

        @Volatile
        private var INSTANCE:ContactDatabase?=null

        fun getInstance(context: Context): ContactDatabase{
            synchronized(this){
                if(INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context,
                        ContactDatabase::class.java,
                        "contactDB")
                        .addMigrations(migrate_1_2)
                        .build()
                }
            }

            return INSTANCE!!
        }
    }
}