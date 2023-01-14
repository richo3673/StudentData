package com.example.studentdata.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.studentdata.model.MahasiswaModel

class MahasiswaViewModel : ViewModel() {

    var mahasiswaListData: MutableLiveData<ArrayList<MahasiswaModel>>? = null
    val mahasiswaList = ArrayList<MahasiswaModel>()

    //GET LIVEDATA
    internal fun getMahasiswaList(): MutableLiveData<ArrayList<MahasiswaModel>> {
        if (mahasiswaListData == null) {
            mahasiswaListData = MutableLiveData()
            createMahasiswa()
        }
        return mahasiswaListData as MutableLiveData<ArrayList<MahasiswaModel>>
    }

    //ADD MODEL TO LIVEDATA
    private fun addData(array: ArrayList<MahasiswaModel>) {
        mahasiswaListData!!.value = array
    }

    //CREATE MODEL
    fun addMahasiswa(nama: String, nim: String, kelas:String) {
        mahasiswaList.add(MahasiswaModel(nama, nim, kelas))
        addData(mahasiswaList)
    }


    //CREATE DUMMY DATA
    private fun createMahasiswa() {
        addMahasiswa("Richo Wijaya Putra", "205150200111018", "TIF-A")
        addMahasiswa("Erling Haaland", "205150200113012", "TEKKOM-B")
        addMahasiswa("Kevin de Bruyne", "205150200117011", "TIF-C")
        addMahasiswa("Antony Santos", "205150200112902", "SI-A")
    }
}