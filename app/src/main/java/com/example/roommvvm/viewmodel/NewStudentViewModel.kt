package com.example.roommvvm.viewmodel

import android.app.Application
import android.graphics.Movie
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roommvvm.data.AppDatabase
import com.example.roommvvm.entity.Student
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

class NewStudentViewModel(application: Application) : AndroidViewModel(application) {

    //deklarasi variable untuk memanggil database dan tabel student
    private val mDb: AppDatabase? = AppDatabase.getInstance(application)
    private val allStudent = MutableLiveData<List<Student>>()

    fun storeMovie(title: String) {

        //memasukan data tabel ke variabel
        val student = Student()
        student.name = title

        GlobalScope.launch {
            mDb?.studentDao()?.insertStudent(student)
        }
    }

    //fungsi mengambil dan menampilkan data
    fun retrieveStudent(): LiveData<List<Student>> {

        //menjalankan query select di student dao
        GlobalScope.launch {
            val list = mDb?.studentDao()?.getAll()

            //saat query dijalankan menghasilkan feedback berupa count data dari table student
            Timber.i("Data yang ada sejumlah {${list?.size}}")
            allStudent.postValue(list)
        }

        return allStudent
    }
}