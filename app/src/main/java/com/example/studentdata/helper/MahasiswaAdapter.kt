package com.example.studentdata.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.studentdata.R
import com.example.studentdata.model.MahasiswaModel

class MahasiswaAdapter(var mahasiswaList: LiveData<List<MahasiswaModel>>, val context: Context):
    RecyclerView.Adapter<MahasiswaAdapter.MahasiswaViewHolder>() {
    inner class MahasiswaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bindItems(model: MahasiswaModel){
            itemView.findViewById<TextView>(R.id.nama).text = model.nama
            itemView.findViewById<TextView>(R.id.nim).text = model.nim
            itemView.findViewById<TextView>(R.id.kelas).text = model.kelas
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MahasiswaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return MahasiswaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MahasiswaViewHolder, position: Int) {
        mahasiswaList.value!!?.get(position)?.let { holder.bindItems(it) }
    }

    override fun getItemCount(): Int {
        return mahasiswaList.value!!.size
    }
}