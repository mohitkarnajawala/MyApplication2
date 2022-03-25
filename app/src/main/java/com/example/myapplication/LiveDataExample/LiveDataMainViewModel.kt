package com.example.myapplication.LiveDataExample

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LiveDataMainViewModel : ViewModel() {

    private var contactsLiveData=MutableLiveData<MutableList<Contact>>()

    init {

        contactsLiveData.value=creatContact()
    }

    fun getContacts(): LiveData<MutableList<Contact>> {
        return contactsLiveData
    }
    private fun creatContact(): MutableList<Contact>{

            Log.e(TAG, "createContacts")
            val contacts = mutableListOf<Contact>()
            for (i in 1..150)
                contacts.add(Contact("Person$i", i))

            return contacts

    }
}