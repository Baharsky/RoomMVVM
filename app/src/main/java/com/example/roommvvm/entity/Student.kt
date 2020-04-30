package com.example.roommvvm.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//class menentukan struktur dalam tabel student
@Entity
data class Student (
    @PrimaryKey(autoGenerate = true) var id: Int? = null,

    //mendeklarasikan struktur tabel
    @ColumnInfo var name: String = ""
)