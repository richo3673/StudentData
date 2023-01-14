package com.example.studentdata

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.studentdata.helper.MahasiswaAdapter
import com.example.studentdata.model.MahasiswaModel
import com.example.studentdata.viewmodel.MahasiswaViewModel
import com.example.studentdata.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), View.OnClickListener {
    var data = MutableLiveData<List<MahasiswaModel>>()
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button1.performClick()
        filterList(null)

        //button binding
        binding.fab.setOnClickListener(this)
        binding.button1.setOnClickListener(this)
        binding.button2.setOnClickListener(this)
        binding.button3.setOnClickListener(this)
        binding.button4.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.fab -> FormDialogFragment().show(supportFragmentManager, FormDialogFragment.TAG)
            R.id.button1 -> filterList(null)
            R.id.button2 -> filterList("TIF")
            R.id.button3-> filterList("TEKKOM")
            R.id.button4 -> filterList("SI")
        }
    }

    private fun filterList(s: String?) {
        //TAMPIL RECYCLER VIEW DENGAN FILTER
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        val model = ViewModelProvider(this).get(MahasiswaViewModel::class.java)
        model.getMahasiswaList().observe(this) { mahasiswaListData ->
            if(s != null) {
                //filter data based on s
                data.value = mahasiswaListData.filter { it.kelas.contains(s) }
            }else {
                //show all data
                data.value = mahasiswaListData
            }
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = MahasiswaAdapter(data, this)
        }
    }
}