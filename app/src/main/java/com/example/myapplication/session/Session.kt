package com.example.myapplication.session

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import java.lang.RuntimeException

@SuppressLint("NewApi")
class Session(context: Context) {
   private val keyGenParameterSpec=MasterKeys.AES256_GCM_SPEC
   private val masterKeyAlias= MasterKeys.getOrCreate(keyGenParameterSpec)
   private val fileName="Mohit"
     val sharedPreferences= EncryptedSharedPreferences.create(
    fileName,masterKeyAlias,context,
    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM)


    private val edit : SharedPreferences.Editor by lazy {
        sharedPreferences.edit()
    }

    fun <T>save(key:String,value:T){
        when(value){
            is String->{
                edit.putString(key,value)
            }
            is Int->{
                edit.putInt(key,value)
            }
            is Float->{
                edit.putFloat(key,value)
            }
            is Long->{
                edit.putLong(key,value)
            }
            is Boolean->{
                edit.putBoolean(key,value)
            }
            else->{
                throw RuntimeException("Attempting to save non-primitive data")
            }
        }
        edit.apply()
        edit.commit()
    }

    fun <T>getPref(key: String,defaultValue:T):T?{
        val value= sharedPreferences.all[key]as T?
        return value?:defaultValue
    }

    fun clear(){
        //optional , use it in case of need
        edit.clear()
        edit.commit()
    }

    fun remove(key: String){
        edit.remove(key)
    }



}