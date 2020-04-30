package com.example.roommvvm.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.roommvvm.R
import com.example.roommvvm.data.AppDatabase
import com.example.roommvvm.helper.StudentRecyclerAdapter
import com.example.roommvvm.viewmodel.NewStudentViewModel
import kotlinx.android.synthetic.main.fragment_name_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
//class untuk memanggil tampilan fragment
class NameListFragment : Fragment() {

    private var listener: OnFragmentInteractionListener? = null
    private lateinit var mViewModel: NewStudentViewModel

    //fungsi utama dalam class yg dipanggil pertama saat class dipanggil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mViewModel = ViewModelProviders.of(this).get(NewStudentViewModel::class.java)
        mViewModel.retrieveStudent().observe(this, Observer {
            Timber.i("menerima perubahan data ${it.size}")

            rvList.adapter = StudentRecyclerAdapter(it)
        })
    }

    //untuk memanggil view yang dipanggil pertama kali saat class dipanggil
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_name_list, container, false)
    }

    //fungsi pertama yang dipanggil saat vew dipanggil
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvList.layoutManager = LinearLayoutManager(activity)

        btnAdd.setOnClickListener {

            //deklarasi instancedari class student dao
            val dao =  AppDatabase.getInstance(this.context!!)?.studentDao()
            GlobalScope.launch {
                dao?.getAll()
            }

            listener?.goToNewNameFragment()
        }
    }

    //fungsi berjalan saat fragment aktif
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    //fungsi yg berjalansaat fragment dinonaktifka/replace
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun goToNewNameFragment()
    }

    companion object {
        @JvmStatic
        fun newInstance() = NameListFragment()
    }

}
