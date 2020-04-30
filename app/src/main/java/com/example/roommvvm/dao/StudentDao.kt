package com.example.roommvvm.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.roommvvm.entity.Student

//meletakkan query yg dipanggil dalam program
@Dao

interface StudentDao {

    //menampilkan seluruh data daritabel student
    @Query("Select * from student")
    fun getAll(): List<Student>

    //insert data ke tabel student
    @Insert
    fun insertStudent(item: Student)
}